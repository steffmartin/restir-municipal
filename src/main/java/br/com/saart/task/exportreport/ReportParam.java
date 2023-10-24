package br.com.saart.task.exportreport;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ReportParam {

    //RELATORIOS DIRF
    MES_INICIAL(new TaskParam("MES_INICIAL", "Mês Calendário Inicial", TaskParam.ParamClass.INT, true, String.valueOf(YearMonth.now().getMonthValue()))),
    ANO_INICIAL(new TaskParam("ANO_INICIAL", "Ano Calendário Inicial", TaskParam.ParamClass.INT, true, Year.now().minusYears(5).toString())),
    MES_FINAL(new TaskParam("MES_FINAL", "Mês Calendário Final", TaskParam.ParamClass.INT, true, "12")),
    ANO_FINAL(new TaskParam("ANO_FINAL", "Ano Calendário Final", TaskParam.ParamClass.INT, true, Year.now().minusYears(1).toString())),
    CODIGOS_RECEITA(new TaskParam("CODIGOS_RECEITA", "Códigos da Receita", TaskParam.ParamClass.INT_AS_STRING_ARRAY, true, "1708,3208,5952"));

    private final TaskParam paramLine;

    public static List<TaskParam> params(ReportParam... paramLines) {
        return Arrays.stream(paramLines).map(ReportParam::getParamLine).collect(Collectors.toList());
    }

    }

