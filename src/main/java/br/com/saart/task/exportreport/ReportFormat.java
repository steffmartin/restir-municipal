package br.com.saart.task.exportreport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JsonMetadataExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;

import java.io.File;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ReportFormat {

    XLSX("Excel", "xlsx", JRXlsxExporter.class, SimpleOutputStreamExporterOutput.class),
    HTML("HTML", "html", HtmlExporter.class, SimpleHtmlExporterOutput.class),
    PDF("PDF", "pdf", JRPdfExporter.class, SimpleOutputStreamExporterOutput.class),
    DOCX("Word", "docx", JRDocxExporter.class, SimpleOutputStreamExporterOutput.class),
    JSON("JSON", "json", JsonMetadataExporter.class, SimpleJsonExporterOutput.class);

    private final String description;
    private final String extension;
    private final Class<? extends Exporter> exporter;
    private final Class<? extends ExporterOutput> output;

    @Override
    public String toString() {
        return description;
    }

    @SneakyThrows
    public Exporter getExporterInstance() {
        return this.exporter.asSubclass(Exporter.class).newInstance();
    }

    @SneakyThrows
    public ExporterOutput getOutputInstance(File file) {
        return this.output.asSubclass(ExporterOutput.class).getConstructor(File.class)
                .newInstance(file);
    }

    @SneakyThrows
    public ExporterOutput getOutputInstance(OutputStream outputStream) {
        return this.output.asSubclass(ExporterOutput.class).getConstructor(OutputStream.class)
                .newInstance(outputStream);
    }

    public static String valuesList() {
        return Arrays.stream(values()).map(ReportFormat::getDescription)
                .collect(Collectors.joining(", "));
    }
}
