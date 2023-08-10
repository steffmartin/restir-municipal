package br.com.smaconsulting.saart.task.exportreport;

import br.com.smaconsulting.saart.task.TaskParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ReportParam {

    //DATAS
    DT_VDA_INI(new TaskParam("DT_VENDA_INI", "Data de venda inicial", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_VDA_FIN(new TaskParam("DT_VENDA_FIN", "Data de venda final", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_EMI_INI(new TaskParam("DT_EMI_INI", "Data de emissão inicial", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_EMI_FIN(new TaskParam("DT_EMI_FIN", "Data de emissão final", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_ES_INI(new TaskParam("DT_EMI_INI", "Data de entrada/saída inicial", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_ES_FIN(new TaskParam("DT_EMI_FIN", "Data de entrada/saída final", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_AQ_INI(new TaskParam("DT_EMI_INI", "Data de aquisição/prestação inicial", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_AQ_FIN(new TaskParam("DT_EMI_FIN", "Data de aquisição/prestação final", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_ESC_INI(new TaskParam("DT_EMI_INI", "Data de escrituração inicial", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_ESC_FIN(new TaskParam("DT_EMI_FIN", "Data de escrituração final", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_INV(new TaskParam("DT_INV", "Data de inventário", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_INV_A(new TaskParam("INV1.DT_INV", "Data de inventário anterior", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),
    DT_INV_B(new TaskParam("INV2.DT_INV", "Data de inventário posterior", TaskParam.ParamClass.DATE_AS_TIMESTAMP)),

    //CAMPOS IN/NOT IN
    NATUREZAS(new TaskParam("NATUREZAS", "Somente as Naturezas", TaskParam.ParamClass.INT_ARRAY, false, null)),
    COD_PRODUTOS(
            new TaskParam("COD_PRODUTOS", "Somente os Códigos de Produto", TaskParam.ParamClass.STRING_ARRAY, false, null)),
    CFOPS(new TaskParam("CFOPS", "Somente os CFOPs", TaskParam.ParamClass.INT_AS_BIG_DECIMAL_ARRAY, false, null)),
    CFOPS_NOT(new TaskParam("CFOPS_NOT", "Exceto os CFOPs", TaskParam.ParamClass.INT_AS_BIG_DECIMAL_ARRAY, false, null)),
    NCMS(new TaskParam("NCMS", "Somente as NCMs", TaskParam.ParamClass.STRING_ARRAY, false, null)),
    NCMS_NOT(new TaskParam("NCMS_NOT", "Exceto as NCMs", TaskParam.ParamClass.STRING_ARRAY, false, null)),
    CSTS(new TaskParam("CSTS", "Somente os CSTs", TaskParam.ParamClass.STRING_ARRAY, false, null)),
    CSTS_NOT(new TaskParam("CSTS_NOT", "Exceto os CSTs", TaskParam.ParamClass.STRING_ARRAY, false, null)),

    //CAMPOS > < = <> >= <=
    BASE_ICMS(new TaskParam("BASE_ICMS", "Base do ICMS", TaskParam.ParamClass.DOUBLE_COMPARISON, false, null)),
    VLR_ICMS(new TaskParam("VLR_ICMS", "Valor do ICMS", TaskParam.ParamClass.DOUBLE_COMPARISON, false, null)),
    BASE_PIS(new TaskParam("BASE_PIS", "Base do PIS", TaskParam.ParamClass.DOUBLE_COMPARISON, false, null)),
    VLR_PIS(new TaskParam("VLR_PIS", "Valor do PIS", TaskParam.ParamClass.DOUBLE_COMPARISON, false, null)),
    BASE_COFINS(new TaskParam("BASE_COFINS", "Base do COFINS", TaskParam.ParamClass.DOUBLE_COMPARISON, false, null)),
    VLR_COFINS(new TaskParam("VLR_COFINS", "Valor do COFINS", TaskParam.ParamClass.DOUBLE_COMPARISON, false, null)),

    //OPÇÕES
    SHOW_NEGATIVE(
            new TaskParam("PRINT_NEGATIVE", "Considerar valor negativo?", TaskParam.ParamClass.BOOLEAN, false, false)),
    COLORS(new TaskParam("COLORIDO", "Colorir detalhamento?", TaskParam.ParamClass.BOOLEAN, false, true)),
    ONLY_TOTAL(new TaskParam("ONLY_TOTAL", "Mostrar somente os totais?", TaskParam.ParamClass.BOOLEAN, false, false)),
    RESUMO(new TaskParam("RESUMO", "Mostrar tabela resumo?", TaskParam.ParamClass.BOOLEAN, false, false)),

    //SUBTASKS
    SUBTASK_EXP_INV(
            new TaskParam("SUBTASK_ExportErrorsInventario", "Exportar CSV com produtos divergentes?",
                    TaskParam.ParamClass.BOOLEAN, false, true)),
    SUBTASK_UPD_SALDO(new TaskParam("SUBTASK_updateInventario", "Atualizar inventário posterior?",
            TaskParam.ParamClass.BOOLEAN, false, false));

    private final TaskParam paramLine;

    public static List<TaskParam> params(ReportParam... paramLines) {
        return Arrays.stream(paramLines).map(ReportParam::getParamLine).collect(Collectors.toList());
    }

}

