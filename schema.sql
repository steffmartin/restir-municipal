
    create table public.dirf (
       id  serial not null,
        ano_calendario int2,
        ano_referencia int2,
        codigo_leiaute varchar(7),
        data_hora_importacao timestamp,
        dirf_linha int4 default 1,
        fimdirf_linha int4,
        nome_arquivo varchar(255),
        numero_recibo varchar(12),
        retificadora char(1),
        id_declarante serial,
        id_responsavel serial,
        primary key (id)
    )

    create table public.dirf_beneficiario (
       id  serial not null,
        alimentado char(1),
        bairro_dist varchar(20),
        benef_linha int4,
        brpde_tipo int2,
        cep varchar(10),
        cidade varchar(40),
        cod_pais int2,
        codigo_registro varchar(7),
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
        rra_natureza varchar(50),
        primary key (id)
    )

    create table public.dirf_beneficiario_valores (
       beneficiario_id serial not null,
        valor_id serial not null,
        cod_registro varchar(7) not null,
        primary key (beneficiario_id, cod_registro)
    )

    create table public.dirf_declarante (
       id  serial not null,
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
       declarante_id serial not null,
        beneficiario_id serial not null,
        primary key (declarante_id, beneficiario_id)
    )

    create table public.dirf_dtpse (
       id  serial not null,
        cpf varchar(11),
        data_nascimento date,
        linha_dtpse int4,
        nome varchar(60),
        rel_dependencia int2,
        vlr_ano numeric(19, 2) default 0,
        tpse_id serial,
        primary key (id)
    )

    create table public.dirf_dtpse_reembolso (
       dtpse_id serial not null,
        reembolso_id serial not null,
        primary key (dtpse_id, reembolso_id)
    )

    create table public.dirf_fci (
       id  serial not null,
        cnpj varchar(14),
        linha_fci int4,
        nome varchar(150),
        dirf_id serial,
        primary key (id)
    )

    create table public.dirf_fci_beneficiario (
       fci_id serial not null,
        beneficiario_id serial not null,
        primary key (fci_id, beneficiario_id)
    )

    create table public.dirf_inf (
       id  serial not null,
        cpf varchar(11),
        informacoes varchar(500),
        dirf_id serial,
        primary key (id)
    )

    create table public.dirf_infpa (
       id  serial not null,
        cpf varchar(11),
        data_nascimento date,
        idrec_codigo varchar(4),
        idrec_linha int4,
        linha_infpa int4,
        nome varchar(60),
        rel_dependencia int2,
        beneficiario_id serial,
        primary key (id)
    )

    create table public.dirf_infpa_valores (
       infpa_id serial not null,
        valor_id serial not null,
        cod_registro varchar(7) not null,
        primary key (infpa_id, cod_registro)
    )

    create table public.dirf_infpc (
       id  serial not null,
        cnpj varchar(14),
        idrec_codigo varchar(4),
        idrec_linha int4,
        linha_infpc int4,
        nome varchar(150),
        beneficiario_id serial,
        primary key (id)
    )

    create table public.dirf_infpc_valores (
       infpc_id serial not null,
        valor_id serial not null,
        cod_registro varchar(7) not null,
        primary key (infpc_id, cod_registro)
    )

    create table public.dirf_opse (
       id  serial not null,
        ans varchar(6),
        cnpj varchar(14),
        linha_opse int4,
        linha_pse int4,
        nome varchar(150),
        dirf_id serial,
        primary key (id)
    )

    create table public.dirf_proc (
       id  serial not null,
        cpf_cnpj_adv varchar(14),
        id_justica int2,
        linha_proc int4,
        nome_adv varchar(150),
        num_proc varchar(20),
        tipo_adv int2,
        vlr_adv numeric(19, 2) default 0,
        dirf_id serial,
        primary key (id)
    )

    create table public.dirf_proc_beneficiario (
       proc_id serial not null,
        beneficiario_id serial not null,
        primary key (proc_id, beneficiario_id)
    )

    create table public.dirf_reembolso (
       id  serial not null,
        cod_registro varchar(6),
        cpf_cnp varchar(14),
        linha_registro int4,
        nome varchar(150),
        vlr_ano_cal numeric(19, 2) default 0,
        vlr_anos_ant numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.dirf_responsavel (
       id  serial not null,
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

    create table public.dirf_rra (
       id  serial not null,
        cpf_cnpj_adv varchar(14),
        nome_adv varchar(150),
        numero_requerimento varchar(20),
        tipo_adv int2,
        tipo_rra int2,
        vlr_adv numeric(19, 2) default 0,
        dirf_id serial,
        primary key (id)
    )

    create table public.dirf_rra_beneficiario (
       rra_id serial not null,
        beneficiario_id serial not null,
        primary key (rra_id, beneficiario_id)
    )

    create table public.dirf_scp (
       id  serial not null,
        cnpj varchar(14),
        nome varchar(150),
        dirf_id serial,
        primary key (id)
    )

    create table public.dirf_scp_beneficiario (
       scp_id serial not null,
        beneficiario_id serial not null,
        primary key (scp_id, beneficiario_id)
    )

    create table public.dirf_tpse (
       id  serial not null,
        cpf varchar(11),
        linha_tpse int4,
        nome varchar(60),
        vlr_ano numeric(19, 2) default 0,
        opse_id serial,
        primary key (id)
    )

    create table public.dirf_tpse_reembolso (
       tpse_id serial not null,
        reembolso_id serial not null,
        primary key (tpse_id, reembolso_id)
    )

    create table public.dirf_valores (
       id  serial not null,
        abr numeric(19, 2) default 0,
        ago numeric(19, 2) default 0,
        cod_registro varchar(7),
        dec_ter numeric(19, 2) default 0,
        descricao varchar(60),
        dez numeric(19, 2) default 0,
        fev numeric(19, 2) default 0,
        idrec_codigo varchar(4),
        idrec_linha int4,
        jan numeric(19, 2) default 0,
        jul numeric(19, 2) default 0,
        jun numeric(19, 2) default 0,
        linha_registro int4,
        mai numeric(19, 2) default 0,
        mar numeric(19, 2) default 0,
        nov numeric(19, 2) default 0,
        out numeric(19, 2) default 0,
        set numeric(19, 2) default 0,
        tipo_valor varchar(6),
        vlr_ano numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.dirf_vrpde (
       id  serial not null,
        cod_rec varchar(4),
        data_pgto date,
        forma_tribut int2,
        linha_vrpde int4,
        tipo_rendimento int2,
        vlr_rendimento numeric(19, 2) default 0,
        vlr_retido numeric(19, 2) default 0,
        beneficiario_id serial,
        primary key (id)
    )

    alter table public.dirf_beneficiario_valores 
       add constraint UK_d5cbdnvu4enrt47bcdk9vk5lo unique (valor_id)

    alter table public.dirf_declarante_beneficiario 
       add constraint UK_o6el951oj9lipr5s2w4wrq4gc unique (beneficiario_id)

    alter table public.dirf_dtpse_reembolso 
       add constraint UK_g246f7x567qqe3as0je868864 unique (reembolso_id)

    alter table public.dirf_fci_beneficiario 
       add constraint UK_71mg7fukqi71o98jujmxopitg unique (beneficiario_id)

    alter table public.dirf_infpa_valores 
       add constraint UK_dn0xpdtxnml0kg92qv5x4m7yk unique (valor_id)

    alter table public.dirf_infpc_valores 
       add constraint UK_hmuh4sj9onyaq1vpeb7ib928g unique (valor_id)

    alter table public.dirf_proc_beneficiario 
       add constraint UK_oq3qek5dp3qtm691fpbjs8d6r unique (beneficiario_id)

    alter table public.dirf_rra_beneficiario 
       add constraint UK_lr9asm3wo2pwaavm41s1i0nta unique (beneficiario_id)

    alter table public.dirf_scp_beneficiario 
       add constraint UK_p9nublq43d30ebm4uye1u5db5 unique (beneficiario_id)

    alter table public.dirf_tpse_reembolso 
       add constraint UK_rs3pv34fyunlfw94i1hx86dy9 unique (reembolso_id)

    alter table public.dirf 
       add constraint FKbutf2g7pgjd07no8kkbjfc6ts 
       foreign key (id_declarante) 
       references public.dirf_declarante

    alter table public.dirf 
       add constraint FK4bh8nuwuce7xemha9lvx2l1x6 
       foreign key (id_responsavel) 
       references public.dirf_responsavel

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

    alter table public.dirf_dtpse 
       add constraint FKnf0gba0vsif41fv27kturmjnk 
       foreign key (tpse_id) 
       references public.dirf_tpse

    alter table public.dirf_dtpse_reembolso 
       add constraint FKiqj6lmvivwmeiryy5plmtodwg 
       foreign key (reembolso_id) 
       references public.dirf_reembolso

    alter table public.dirf_dtpse_reembolso 
       add constraint FKb2m9lwxawp86l02msbw5tdwj7 
       foreign key (dtpse_id) 
       references public.dirf_dtpse

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

    alter table public.dirf_infpa 
       add constraint FK28yggrq1tjux5hwkexpg56y9n 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_infpa_valores 
       add constraint FK6joe8d2qep3dagk1ojbjae2ks 
       foreign key (valor_id) 
       references public.dirf_valores

    alter table public.dirf_infpa_valores 
       add constraint FK7wjn52vu81ryygh67dn6uvk7j 
       foreign key (infpa_id) 
       references public.dirf_infpa

    alter table public.dirf_infpc 
       add constraint FKhusmc89x7m2fd8nbdcxnw5w6f 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_infpc_valores 
       add constraint FKdgx313vxk9rmlbjn5b5flyq1w 
       foreign key (valor_id) 
       references public.dirf_valores

    alter table public.dirf_infpc_valores 
       add constraint FK2ytba3fo0h74p1gcgwnj8siyf 
       foreign key (infpc_id) 
       references public.dirf_infpc

    alter table public.dirf_opse 
       add constraint FKbpoorw17knpng66dtsqc5a6vi 
       foreign key (dirf_id) 
       references public.dirf

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

    alter table public.dirf_rra 
       add constraint FKbrwsct1rmphf4j4sn2be89k4a 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_rra_beneficiario 
       add constraint FKbhxgr6nwjgl6pmcflmfxn1ami 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_rra_beneficiario 
       add constraint FKda93tdw40xbtedgg7s3tsbev 
       foreign key (rra_id) 
       references public.dirf_rra

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

    alter table public.dirf_tpse 
       add constraint FKpiepscwtodo1c0qo4mbo89ld 
       foreign key (opse_id) 
       references public.dirf_opse

    alter table public.dirf_tpse_reembolso 
       add constraint FKpt3a8jiapd892kqbqfuady9dd 
       foreign key (reembolso_id) 
       references public.dirf_reembolso

    alter table public.dirf_tpse_reembolso 
       add constraint FKsna6v67fidpdigx4ekb2kq5it 
       foreign key (tpse_id) 
       references public.dirf_tpse

    alter table public.dirf_vrpde 
       add constraint FKoilkqnksfp9dk5r7q8toinnf5 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario
