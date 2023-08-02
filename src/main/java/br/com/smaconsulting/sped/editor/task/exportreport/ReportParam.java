package br.com.smaconsulting.sped.editor.task.exportreport;

import br.com.smaconsulting.sped.editor.task.TaskParam;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.smaconsulting.sped.editor.task.TaskParam.ParamClass.*;

@AllArgsConstructor
@Getter
public enum ReportParam {

    //DATAS
    DT_VDA_INI(new TaskParam("DT_VENDA_INI", "Data de venda inicial", DATE_AS_TIMESTAMP)),
    DT_VDA_FIN(new TaskParam("DT_VENDA_FIN", "Data de venda final", DATE_AS_TIMESTAMP)),
    DT_EMI_INI(new TaskParam("DT_EMI_INI", "Data de emissão inicial", DATE_AS_TIMESTAMP)),
    DT_EMI_FIN(new TaskParam("DT_EMI_FIN", "Data de emissão final", DATE_AS_TIMESTAMP)),
    DT_ES_INI(new TaskParam("DT_EMI_INI", "Data de entrada/saída inicial", DATE_AS_TIMESTAMP)),
    DT_ES_FIN(new TaskParam("DT_EMI_FIN", "Data de entrada/saída final", DATE_AS_TIMESTAMP)),
    DT_AQ_INI(new TaskParam("DT_EMI_INI", "Data de aquisição/prestação inicial", DATE_AS_TIMESTAMP)),
    DT_AQ_FIN(new TaskParam("DT_EMI_FIN", "Data de aquisição/prestação final", DATE_AS_TIMESTAMP)),
    DT_ESC_INI(new TaskParam("DT_EMI_INI", "Data de escrituração inicial", DATE_AS_TIMESTAMP)),
    DT_ESC_FIN(new TaskParam("DT_EMI_FIN", "Data de escrituração final", DATE_AS_TIMESTAMP)),
    DT_INV(new TaskParam("DT_INV", "Data de inventário", DATE_AS_TIMESTAMP)),
    DT_INV_A(new TaskParam("INV1.DT_INV", "Data de inventário anterior", DATE_AS_TIMESTAMP)),
    DT_INV_B(new TaskParam("INV2.DT_INV", "Data de inventário posterior", DATE_AS_TIMESTAMP)),

    //CAMPOS IN/NOT IN
    NATUREZAS(new TaskParam("NATUREZAS", "Somente as Naturezas", INT_ARRAY, false, null)),
    COD_PRODUTOS(
            new TaskParam("COD_PRODUTOS", "Somente os Códigos de Produto", STRING_ARRAY, false, null)),
    CFOPS(new TaskParam("CFOPS", "Somente os CFOPs", INT_AS_BIG_DECIMAL_ARRAY, false, null)),
    CFOPS_NOT(new TaskParam("CFOPS_NOT", "Exceto os CFOPs", INT_AS_BIG_DECIMAL_ARRAY, false, null)),
    NCMS(new TaskParam("NCMS", "Somente as NCMs", STRING_ARRAY, false, null)),
    NCMS_NOT(new TaskParam("NCMS_NOT", "Exceto as NCMs", STRING_ARRAY, false, null)),
    CSTS(new TaskParam("CSTS", "Somente os CSTs", STRING_ARRAY, false, null)),
    CSTS_NOT(new TaskParam("CSTS_NOT", "Exceto os CSTs", STRING_ARRAY, false, null)),

    //CAMPOS > < = <> >= <=
    BASE_ICMS(new TaskParam("BASE_ICMS", "Base do ICMS", DOUBLE_COMPARISON, false, null)),
    VLR_ICMS(new TaskParam("VLR_ICMS", "Valor do ICMS", DOUBLE_COMPARISON, false, null)),
    BASE_PIS(new TaskParam("BASE_PIS", "Base do PIS", DOUBLE_COMPARISON, false, null)),
    VLR_PIS(new TaskParam("VLR_PIS", "Valor do PIS", DOUBLE_COMPARISON, false, null)),
    BASE_COFINS(new TaskParam("BASE_COFINS", "Base do COFINS", DOUBLE_COMPARISON, false, null)),
    VLR_COFINS(new TaskParam("VLR_COFINS", "Valor do COFINS", DOUBLE_COMPARISON, false, null)),

    //OPÇÕES
    SHOW_NEGATIVE(
            new TaskParam("PRINT_NEGATIVE", "Considerar valor negativo?", BOOLEAN, false, false)),
    COLORS(new TaskParam("COLORIDO", "Colorir detalhamento?", BOOLEAN, false, true)),
    ONLY_TOTAL(new TaskParam("ONLY_TOTAL", "Mostrar somente os totais?", BOOLEAN, false, false)),
    RESUMO(new TaskParam("RESUMO", "Mostrar tabela resumo?", BOOLEAN, false, false)),

    //SUBTASKS
    SUBTASK_EXP_INV(
            new TaskParam("SUBTASK_ExportErrorsInventario", "Exportar CSV com produtos divergentes?",
                    BOOLEAN, false, true)),
    SUBTASK_UPD_SALDO(new TaskParam("SUBTASK_updateInventario", "Atualizar inventário posterior?",
            BOOLEAN, false, false));

    private final TaskParam paramLine;

    public static List<TaskParam> params(ReportParam... paramLines) {
        return Arrays.stream(paramLines).map(ReportParam::getParamLine).collect(Collectors.toList());
    }

}

