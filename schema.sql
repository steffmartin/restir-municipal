
    create table public.dirf (
       id  bigserial not null,
        ano_calendario int2 not null,
        ano_referencia int2 not null,
        codigo_leiaute varchar(7),
        dirf_linha int4 default 1,
        fimdirf_linha int4,
        importado_em timestamp,
        nome_arquivo varchar(255),
        numero_recibo varchar(12),
        retificadora char(1),
        id_declarante int8 not null,
        id_responsavel int8 not null,
        primary key (id)
    )

    create table public.dirf_acum (
       id  bigserial not null,
        cpf_cnpj_adv varchar(14),
        nome_adv varchar(150),
        numero_requerimento varchar(20),
        rra_linha int4,
        tipo_adv int2,
        tipo_rra int2,
        vlr_adv numeric(19, 2) default 0,
        dirf_id int8 not null,
        primary key (id)
    )

    create table public.dirf_acum_beneficiario (
       acum_id int8 not null,
        beneficiario_id int8 not null,
        primary key (acum_id, beneficiario_id)
    )

    create table public.dirf_alimentado (
       id  bigserial not null,
        cpf varchar(11),
        data_nascimento date,
        infpa_linha int4,
        nome varchar(60),
        rel_dependencia int2,
        beneficiario_id int8 not null,
        primary key (id)
    )

    create table public.dirf_alimentado_valores (
       alimentado_id int8 not null,
        valor_id int8 not null,
        cod_registro varchar(7) not null,
        primary key (alimentado_id, cod_registro)
    )

    create table public.dirf_beneficiario (
       id  bigserial not null,
        alimentado char(1),
        bairro_dist varchar(20),
        benef_linha int4,
        brpde_tipo int2,
        cep varchar(10),
        cidade varchar(40),
        cod_pais int2,
        codigo_registro varchar(7) not null,
        complemento varchar(25),
        cpf_cnpj varchar(14),
        data_laudo date,
        estado varchar(40),
        fone varchar(15),
        logradouro varchar(60),
        nif varchar(30),
        nif_dispensado char(1),
        nif_opcional char(1),
        nome varchar(150),
        numero varchar(6),
        participacao float4,
        prev_compl char(1),
        rel_fonte_benef int2,
        rpde_linha int4,
        rra_natureza varchar(50),
        primary key (id)
    )

    create table public.dirf_beneficiario_valores (
       beneficiario_id int8 not null,
        valor_id int8 not null,
        cod_registro varchar(7) not null,
        primary key (beneficiario_id, cod_registro)
    )

    create table public.dirf_declarante (
       id  bigserial not null,
        cpf_cnpj varchar(14),
        cpf_inventariante varchar(11),
        cpf_responsavel_pj varchar(11),
        data_espolio_saida date,
        data_obito date,
        data_sit_especial date,
        dec_linha int4 default 3,
        decisao_judicial char(1),
        decpf_tipo_evento int2,
        decpj_natureza int2,
        espolio_saida char(1),
        falecido char(1),
        fund_publica_dir_privado char(1),
        fundo_clube_investimento char(1),
        nome varchar(150),
        nome_inventariante varchar(60),
        pgto_isentas_imunes char(1),
        plano_saude char(1),
        rendimento_exterior char(1),
        servicos_notoriais char(1),
        sit_especial char(1),
        sit_espolio int2,
        socio_ostensivo char(1),
        primary key (id)
    )

    create table public.dirf_declarante_beneficiario (
       declarante_id int8 not null,
        beneficiario_id int8 not null,
        primary key (declarante_id, beneficiario_id)
    )

    create table public.dirf_fci (
       id  bigserial not null,
        cnpj varchar(14),
        fci_linha int4,
        nome varchar(150),
        dirf_id int8 not null,
        primary key (id)
    )

    create table public.dirf_fci_beneficiario (
       fci_id int8 not null,
        beneficiario_id int8 not null,
        primary key (fci_id, beneficiario_id)
    )

    create table public.dirf_inf (
       id  bigserial not null,
        cpf varchar(11),
        inf_linha int4,
        informacoes varchar(500),
        dirf_id int8 not null,
        primary key (id)
    )

    create table public.dirf_prev_compl (
       id  bigserial not null,
        cnpj varchar(14),
        infpc_linha int4,
        nome varchar(150),
        beneficiario_id int8 not null,
        primary key (id)
    )

    create table public.dirf_prev_compl_valores (
       prev_compl_id int8 not null,
        valor_id int8 not null,
        cod_registro varchar(7) not null,
        primary key (prev_compl_id, cod_registro)
    )

    create table public.dirf_proc (
       id  bigserial not null,
        cpf_cnpj_adv varchar(14),
        nome_adv varchar(150),
        num_proc varchar(20),
        proc_linha int4,
        tipo_adv int2,
        tipo_justica int2,
        vlr_adv numeric(19, 2) default 0,
        dirf_id int8 not null,
        primary key (id)
    )

    create table public.dirf_proc_beneficiario (
       proc_id int8 not null,
        beneficiario_id int8 not null,
        primary key (proc_id, beneficiario_id)
    )

    create table public.dirf_responsavel (
       id  bigserial not null,
        cpf varchar(11),
        ddd varchar(2),
        email varchar(50),
        fax varchar(9),
        fone varchar(9),
        nome varchar(60),
        ramal varchar(6),
        respo_linha int4 default 2,
        primary key (id)
    )

    create table public.dirf_saude (
       id  bigserial not null,
        ans varchar(6),
        cnpj varchar(14),
        nome varchar(150),
        opse_linha int4,
        pse_linha int4,
        dirf_id int8 not null,
        primary key (id)
    )

    create table public.dirf_saude_dep (
       id  bigserial not null,
        cpf varchar(11),
        data_nascimento date,
        dtpse_linha int4,
        nome varchar(60),
        rel_dependencia int2,
        vlr_ano numeric(19, 2) default 0,
        saude_tit_id int8 not null,
        primary key (id)
    )

    create table public.dirf_saude_dep_inf (
       saude_dep_id int8 not null,
        saude_inf_id int8 not null,
        primary key (saude_dep_id, saude_inf_id)
    )

    create table public.dirf_saude_inf (
       id  bigserial not null,
        cod_registro varchar(6) not null,
        cpf_cnpj varchar(14),
        linha_registro int4,
        nome varchar(150),
        vlr_ano_cal numeric(19, 2) default 0,
        vlr_anos_ant numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.dirf_saude_tit (
       id  bigserial not null,
        cpf varchar(11),
        nome varchar(60),
        tpse_linha int4,
        vlr_ano numeric(19, 2) default 0,
        saude_id int8 not null,
        primary key (id)
    )

    create table public.dirf_saude_tit_inf (
       saude_tit_id int8 not null,
        saude_inf_id int8 not null,
        primary key (saude_tit_id, saude_inf_id)
    )

    create table public.dirf_scp (
       id  bigserial not null,
        cnpj varchar(14),
        nome varchar(150),
        scp_linha int4,
        dirf_id int8 not null,
        primary key (id)
    )

    create table public.dirf_scp_beneficiario (
       scp_id int8 not null,
        beneficiario_id int8 not null,
        primary key (scp_id, beneficiario_id)
    )

    create table public.dirf_valores (
       id  bigserial not null,
        abr numeric(19, 2) default 0,
        ago numeric(19, 2) default 0,
        cod_registro varchar(7) not null,
        dec_ter numeric(19, 2) default 0,
        descricao varchar(60),
        dez numeric(19, 2) default 0,
        fev numeric(19, 2) default 0,
        idrec_codigo varchar(4),
        idrec_linha int4,
        jan numeric(19, 2) default 0,
        jul numeric(19, 2) default 0,
        jun numeric(19, 2) default 0,
        mai numeric(19, 2) default 0,
        mar numeric(19, 2) default 0,
        nov numeric(19, 2) default 0,
        out numeric(19, 2) default 0,
        registro_linha int4,
        set numeric(19, 2) default 0,
        tipo_valor varchar(6) not null,
        vlr_ano numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.dirf_valores_ext (
       id  bigserial not null,
        cod_rec varchar(4),
        data_pgto date,
        forma_tribut int2,
        tipo_rendimento int2,
        vlr_rendimento numeric(19, 2) default 0,
        vlr_retido numeric(19, 2) default 0,
        vrpde_linha int4,
        beneficiario_id int8 not null,
        primary key (id)
    )

    alter table public.dirf_acum_beneficiario 
       add constraint UK_fj195dc6x3bjmtjnngs06iwun unique (beneficiario_id)

    alter table public.dirf_alimentado_valores 
       add constraint UK_m3bw6trcyvi8rhxnn3ffnmtc1 unique (valor_id)

    alter table public.dirf_beneficiario_valores 
       add constraint UK_d5cbdnvu4enrt47bcdk9vk5lo unique (valor_id)

    alter table public.dirf_declarante_beneficiario 
       add constraint UK_o6el951oj9lipr5s2w4wrq4gc unique (beneficiario_id)

    alter table public.dirf_fci_beneficiario 
       add constraint UK_71mg7fukqi71o98jujmxopitg unique (beneficiario_id)

    alter table public.dirf_prev_compl_valores 
       add constraint UK_fmtr0xl87dwn57bfjxoeh67yi unique (valor_id)

    alter table public.dirf_proc_beneficiario 
       add constraint UK_oq3qek5dp3qtm691fpbjs8d6r unique (beneficiario_id)

    alter table public.dirf_saude_dep_inf 
       add constraint UK_mdh3ppkcpdxpr64ydvkt1n348 unique (saude_inf_id)

    alter table public.dirf_saude_tit_inf 
       add constraint UK_7xcyhn2wb1y0u3m0wj66qn5qw unique (saude_inf_id)

    alter table public.dirf_scp_beneficiario 
       add constraint UK_p9nublq43d30ebm4uye1u5db5 unique (beneficiario_id)

    alter table public.dirf 
       add constraint FKbutf2g7pgjd07no8kkbjfc6ts 
       foreign key (id_declarante) 
       references public.dirf_declarante

    alter table public.dirf 
       add constraint FK4bh8nuwuce7xemha9lvx2l1x6 
       foreign key (id_responsavel) 
       references public.dirf_responsavel

    alter table public.dirf_acum 
       add constraint FKrfogift1avfjsjfsydwiio5qw 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_acum_beneficiario 
       add constraint FKrpj4pamvxorw4pknm9vqtv6fg 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_acum_beneficiario 
       add constraint FKpmnoqxiukt209euisq49sa3bn 
       foreign key (acum_id) 
       references public.dirf_acum

    alter table public.dirf_alimentado 
       add constraint FKq0wo2lpf3a480ovyj4ftshc2c 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_alimentado_valores 
       add constraint FKkh2c972xr0jl3dajgp6xlk05s 
       foreign key (valor_id) 
       references public.dirf_valores

    alter table public.dirf_alimentado_valores 
       add constraint FKtbj44l7gv2fhr9qr10l1w484s 
       foreign key (alimentado_id) 
       references public.dirf_alimentado

    alter table public.dirf_beneficiario_valores 
       add constraint FKcnpin9idry1ein8yh9kn001cx 
       foreign key (valor_id) 
       references public.dirf_valores

    alter table public.dirf_beneficiario_valores 
       add constraint FK7q1r6g6i7y5sh2upvy362nb6f 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_declarante_beneficiario 
       add constraint FKo481o9dgau622uqvwt2t6l0t8 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_declarante_beneficiario 
       add constraint FKkpqdhwwl56svc985t1j2xmyp1 
       foreign key (declarante_id) 
       references public.dirf_declarante

    alter table public.dirf_fci 
       add constraint FK6b9oeqt06lujegtj2cxc7e7h2 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_fci_beneficiario 
       add constraint FKcjuwppxbrve6mcs1c9eafau69 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_fci_beneficiario 
       add constraint FK1rwtmq6papepg50hrhng3lxro 
       foreign key (fci_id) 
       references public.dirf_fci

    alter table public.dirf_inf 
       add constraint FK54l8g6qy8cgqu4gb9t7q9xqi6 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_prev_compl 
       add constraint FKrx5hj37vregrcyrk42iyxaluy 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_prev_compl_valores 
       add constraint FKaetb9iwdic3tc9t8rdp5p69v2 
       foreign key (valor_id) 
       references public.dirf_valores

    alter table public.dirf_prev_compl_valores 
       add constraint FKrcoiq981wse16heh45i3d5jh1 
       foreign key (prev_compl_id) 
       references public.dirf_prev_compl

    alter table public.dirf_proc 
       add constraint FKdfmbvggklyxydd5exi7q1lrk5 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_proc_beneficiario 
       add constraint FK80pt06mxwdkqiurqtvx9ron65 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_proc_beneficiario 
       add constraint FKcq9lm8fu6sgh05bm6v1oxpluf 
       foreign key (proc_id) 
       references public.dirf_proc

    alter table public.dirf_saude 
       add constraint FKdahef8vr3vf2epsp1tnoig1hc 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_saude_dep 
       add constraint FK3csf1p8mbecae4jkjudlonx6d 
       foreign key (saude_tit_id) 
       references public.dirf_saude_tit

    alter table public.dirf_saude_dep_inf 
       add constraint FKnf7cdfqrqqmm0paa9wba2hgoe 
       foreign key (saude_inf_id) 
       references public.dirf_saude_inf

    alter table public.dirf_saude_dep_inf 
       add constraint FKcice7erwt323hvelo8mxw8a9b 
       foreign key (saude_dep_id) 
       references public.dirf_saude_dep

    alter table public.dirf_saude_tit 
       add constraint FKmc14wolsto1exmrcul9d1ryi 
       foreign key (saude_id) 
       references public.dirf_saude

    alter table public.dirf_saude_tit_inf 
       add constraint FK9m4cxm6xn8cbf8au3tnid7qka 
       foreign key (saude_inf_id) 
       references public.dirf_saude_inf

    alter table public.dirf_saude_tit_inf 
       add constraint FKskinr0rarqtsu7mro1l7as19j 
       foreign key (saude_tit_id) 
       references public.dirf_saude_tit

    alter table public.dirf_scp 
       add constraint FKrrsfcx5fuao8i677lcejlmjyw 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_scp_beneficiario 
       add constraint FKokjj3wh13lrindl3kpdc45c04 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_scp_beneficiario 
       add constraint FK57dvbb2o4eqkei4r2nyqc1vxw 
       foreign key (scp_id) 
       references public.dirf_scp

    alter table public.dirf_valores_ext 
       add constraint FK7901kwj82935v861fgo9ow8pc 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario
