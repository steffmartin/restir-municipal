package br.com.saart.task.exportreport;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum ReportName {


    AQS_EGA_CTB("1", ReportParam.params(ReportParam.DT_ES_INI, ReportParam.DT_ES_FIN, ReportParam.ONLY_TOTAL),
            "Aquisição de Energia, Gás ou Água - SPED Contribuições", false);

    private final String subItem;
    private final List<TaskParam> params;
    private final String description;
    private final boolean compilado;

    @Override
    public String toString() {
        return subItem + " - " + description;
    }

    public static ReportName valueOfOrNull(String name) {
        try {
            return ReportName.valueOf(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}

