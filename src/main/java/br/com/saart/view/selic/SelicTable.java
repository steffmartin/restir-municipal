package br.com.saart.view.selic;

import br.com.saart.entity.selic.Selic;
import br.com.saart.task.filtros.Coluna;
import br.com.saart.util.Util;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SelicTable {

    public static final List<Coluna> COLUNAS = new ArrayList<>();

    static {
        COLUNAS.add(new Coluna("id", "# ID", Long.class));
        COLUNAS.add(new Coluna("periodo", "Período", LocalDate.class));
        COLUNAS.add(new Coluna("valor", "Valor da SELIC", BigDecimal.class));
    }

    public SelicTable(Selic selic) {
        this.selicId = selic.getId().toString();
        this.periodo = Util.toDMY(selic.getPeriodo());
        this.valor = Util.toNumberString(selic.getValor());
    }

    private String selicId;
    private String periodo;
    private String valor;

}
