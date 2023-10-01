package br.com.saart.task.exportreport;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static br.com.saart.task.exportreport.ReportParam.*;

@AllArgsConstructor
@Getter
public enum ReportName {


    SAART_IRRF_POR_BENEFICIARIO("DIRF", ReportParam.params(MES_INICIAL, ANO_INICIAL, MES_FINAL, ANO_FINAL, CODIGOS_RECEITA),
            "Relatório de Restituição de IRRF por Beneficiário - DIRF", false),

    SAART_IRRF_POR_PERIODO("DIRF", ReportParam.params(MES_INICIAL, ANO_INICIAL, MES_FINAL, ANO_FINAL, CODIGOS_RECEITA),
            "Relatório de Restituição de IRRF por Período - DIRF", false);

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

