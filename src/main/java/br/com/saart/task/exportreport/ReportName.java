package br.com.saart.task.exportreport;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.saart.task.exportreport.ReportParam.*;

@AllArgsConstructor
@Getter
public enum ReportName {


    SAART_IRRF_POR_PERIODO("DIRF", ReportParam.params(MES_INICIAL, ANO_INICIAL, MES_FINAL, ANO_FINAL, CODIGOS_RECEITA),
            "Agrupado por Ano Calendário", false),
    SAART_IRRF_POR_BENEFICIARIO("DIRF", ReportParam.params(MES_INICIAL, ANO_INICIAL, MES_FINAL, ANO_FINAL, CODIGOS_RECEITA),
            "Agrupado por Beneficiário", false);

    private final String group;
    private final List<TaskParam> params;
    private final String description;
    private final boolean compilado;

    @Override
    public String toString() {
        return description;
    }

    public static List<ReportName> ofGroup(String group) {
        return Arrays.stream(ReportName.values()).filter(report -> report.group.equalsIgnoreCase(group))
                .collect(Collectors.toList());
    }

    public static ReportName valueOfOrNull(String name) {
        try {
            return ReportName.valueOf(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}

