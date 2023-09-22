--Adiciona coluna valor_acumulado na tabela da SELIC
alter table public.selic
add column valor_acumulado numeric(38,2);

--Cria uma função para preencher a valor_acumulado
CREATE OR REPLACE FUNCTION public.recalcular_selic_acumulada() RETURNS void AS $$
BEGIN
    UPDATE public.selic AS s
    SET valor_acumulado = (
        SELECT 1 + COALESCE(SUM(valor),0)
        FROM public.selic
        WHERE periodo > s.periodo
    );
END;
$$ LANGUAGE plpgsql;

--Cria uma função para retornar a a função acima para uma trigger
CREATE OR REPLACE FUNCTION public.trg_recalcular_selic_acumulada()
    RETURNS TRIGGER AS $$
BEGIN
    PERFORM public.recalcular_selic_acumulada();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--Cria uma trigger para chamar a função acima sempre que houver alteração na tabela selic
create or replace trigger alteracao_selic_trigger
after insert or update or delete
on public.selic
WHEN (pg_trigger_depth() < 1)
execute function public.trg_recalcular_selic_acumulada();

--Cria uma função para atualizar um valor pela selic
CREATE OR REPLACE FUNCTION public.aplicar_selic(
    valor numeric(38, 2),
    mes int,
    ano int2
) RETURNS numeric(38, 2) AS $$
DECLARE
    periodo_consulta date;
    acumulada numeric(38, 2);
BEGIN
    if valor = 0 or valor is null THEN
        RETURN valor; -- Se o valor recebido for zero ou null, retorne-o imediatamente
    END IF;

    -- Converta o mês e ano para um período no formato 'yyyy-mm-01'
    periodo_consulta := to_date(ano || '-' || mes || '-01', 'yyyy-mm-dd');

    -- Selecione o valor acumulado diretamente usando a cláusula INTO
    SELECT valor_acumulado INTO acumulada
    FROM public.selic
    WHERE periodo = periodo_consulta;

    -- Calcule o resultado multiplicando o valor pelo valor acumulado
    RETURN round(valor + (valor * acumulada / 100), 2);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Trate a exceção de nenhuma linha encontrada (nenhuma correspondência de período)
        RETURN valor; -- Se não houver correspondência, retorne o valor original
END;
$$ LANGUAGE plpgsql;
