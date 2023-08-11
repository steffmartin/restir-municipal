package br.com.saart.task.exportreport;

import br.com.saart.task.GenericTask;
import br.com.saart.task.exportreport.subtask.ReportSubtask;
import br.com.saart.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ExportReportTask extends GenericTask {

    //Inputs
    private final ReportName reportName;
    private final String outputDir;
    private final ReportFormat reportFormat;
    private final Map<String, Object> params;

    //Subtasks
    @Autowired
    private ApplicationContext context;
    private List<ReportSubtask> subtasks = new ArrayList<>();

    //Service
    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional(readOnly = true)
    protected Map<String, Throwable> call() throws Exception {
        updateMessage("Iniciando...");
        StopWatch watch = new StopWatch();
        watch.start();
        int progress = 0;

        setupVirtualizer();
        addExtraParams();
        prepareSubtasks();

        JasperReport report = Util.loadReportTemplate(reportName);

        updateMessage("Executando consulta ao banco de dados...");

        JasperPrint jasperPrint;

        try (Connection connection = dataSource.getConnection()) {
            jasperPrint = JasperFillManager.fillReport(report, params, connection);
        } catch (Exception e) {
            closeVirtualizer();
            throw e;
        }
        updateProgress(++progress, 2 + subtasks.size());

        boolean hasPages = CollectionUtils.isNotEmpty(jasperPrint.getPages());

        if (hasPages) {
            updateMessage("Montando e gravando o relatório...");

            String fileName = generateFileName();
            Exporter exporter = reportFormat.getExporterInstance();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(getOutputStream(fileName));

            exporter.exportReport();
            updateProgress(++progress, 2 + subtasks.size());

            if (CollectionUtils.isNotEmpty(subtasks)) {
                try {
                    for (ReportSubtask subtask : subtasks) {
                        updateMessage(subtask.getDescription());
                        subtask.call(jasperPrint);
                        updateProgress(++progress, 2 + subtasks.size());
                    }
                } catch (Exception e) {
                    closeVirtualizer();
                    throw e;
                }
            }
        }

        closeVirtualizer();
        watch.stop();
        updateProgress(1, 1);
        updateMessage(
                hasPages ? "Processo finalizado! Tempo gasto: " + Util.toHMS(watch.getTotalTimeMillis())
                        : "Não há dados para os parâmetros informados.");
        return errors;
    }

    private void setupVirtualizer() {
        JRSwapFile tempFile = new JRSwapFile(outputDir, 4096, 1024);
        JRVirtualizer virtualizer = new JRSwapFileVirtualizer(300, tempFile);
        JRVirtualizationHelper.setThreadVirtualizer(virtualizer);

        params.put(JRParameter.IS_IGNORE_PAGINATION, false);
        params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
    }

    private void closeVirtualizer() {
        JRVirtualizationHelper.getThreadVirtualizer().cleanup();
    }

    private void addExtraParams() {
        params.put("REPORT_NAME", reportName.getDescription());
    }

    private void prepareSubtasks() {
        params.forEach((nome, valor) -> {
            if (nome.startsWith("SUBTASK_") && ((Boolean) valor)) {
                String subtaskName = StringUtils.removeStart(nome, "SUBTASK_");
                try {
                    addSubtaskFromContext(subtaskName);
                } catch (Exception e) {
                    addNewSubtaskInstance(subtaskName);
                }
            }
        });
    }

    private void addSubtaskFromContext(String beanName) {
        subtasks.add((ReportSubtask) context.getBean(beanName));
    }

    private void addNewSubtaskInstance(String className) {
        try {
            subtasks.add(Class.forName(ReportSubtask.class.getPackage().getName() + "." + className)
                    .asSubclass(ReportSubtask.class).getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private ExporterOutput getOutputStream(String fileName) {
        File file = new File(outputDir + "\\" + fileName + "." + reportFormat.getExtension());
        file.getParentFile().mkdirs();
        return reportFormat.getOutputInstance(file);
    }

    private String generateFileName() {
        return String.join(" - ",
                MapUtils.getString(params, "REPORT_NAME"),
                Util.toMY((Timestamp) Util.getFirst(params, LocalDate.now(), "DT_VENDA_INI", "DT_EMI_INI",
                        "DT_ES_INI", "INV1.DT_INV", "DT_INV")) + " a " +
                        Util.toMY((Timestamp) Util.getFirst(params, LocalDate.now(), "DT_VENDA_FIN", "DT_EMI_FIN",
                                "DT_ES_FIN", "INV2.DT_INV", "DT_INV"))
        ).replaceAll("[\\\\/:*?\"<>|]", "-");
    }
}
