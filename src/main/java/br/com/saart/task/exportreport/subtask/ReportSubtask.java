package br.com.saart.task.exportreport.subtask;

import net.sf.jasperreports.engine.JasperPrint;

public abstract class ReportSubtask {

    public abstract void call(JasperPrint jasperPrint) throws Exception;

    public abstract String getDescription();

}
