create table public.dirf_valores_mensais (
     id bigserial not null,
     idrec_linha integer,
     idrec_codigo varchar(4),
     registro_linha integer,
     cod_registro varchar(7) not null,
     ano_calendario smallint,
     mes integer,
     valor numeric(38,2) default 0,
     dirf_valores_id bigint not null,
     primary key (id)
);

alter table if exists public.dirf_valores_mensais
add constraint FK60ibufets1asvwnki63d4apv0
foreign key (dirf_valores_id)
references public.dirf_valores;

--Suporte para indices de texto
CREATE EXTENSION IF NOT EXISTS pg_trgm;

--Indices para otimizar query
CREATE INDEX IF NOT EXISTS idx_ano_calendario_dirf ON public.dirf (ano_calendario);
CREATE INDEX IF NOT EXISTS idx_cpf_cnpj_declarante ON public.dirf_declarante (cpf_cnpj);
CREATE INDEX IF NOT EXISTS idx_cpf_cnpj_beneficiario ON public.dirf_beneficiario (cpf_cnpj);
CREATE INDEX IF NOT EXISTS idx_cod_registro ON public.dirf_beneficiario_valores (cod_registro);
CREATE INDEX IF NOT EXISTS idx_idrec_codigo_valores ON public.dirf_valores (idrec_codigo);
CREATE INDEX IF NOT EXISTS idx_idrec_codigo_mensais ON public.dirf_valores_mensais (idrec_codigo);
CREATE INDEX IF NOT EXISTS idx_dirf_valores_id ON public.dirf_valores_mensais (dirf_valores_id);