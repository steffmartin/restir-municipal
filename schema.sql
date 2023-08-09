
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

    create table public.dirf_fci (
       id  serial not null,
        cnpj varchar(14),
        linha_fci int4,
        nome varchar(150),
        id_dirf serial,
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
        id_dirf serial,
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
        id_dirf serial,
        primary key (id)
    )

    create table public.dirf_proc_beneficiario (
       proc_id serial not null,
        beneficiario_id serial not null,
        primary key (proc_id, beneficiario_id)
    )

    create table public.dirf_pse_opse (
       id  serial not null,
        ans varchar(6),
        cnpj varchar(14),
        linha_opse int4,
        linha_pse int4,
        nome varchar(150),
        id_dirf serial,
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

    create table public.dirf_rpde_beneficiario (
       dirf_id serial not null,
        beneficiario_id serial not null,
        primary key (dirf_id, beneficiario_id)
    )

    create table public.dirf_rra (
       id  serial not null,
        cpf_cnpj_adv varchar(14),
        nome_adv varchar(150),
        numero_requerimento varchar(20),
        tipo_adv int2,
        tipo_rra int2,
        vlr_adv numeric(19, 2) default 0,
        id_dirf serial,
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
        id_dirf serial,
        primary key (id)
    )

    create table public.dirf_scp_beneficiario (
       scp_id serial not null,
        beneficiario_id serial not null,
        primary key (scp_id, beneficiario_id)
    )

    alter table public.dirf_declarante_beneficiario 
       add constraint UK_o6el951oj9lipr5s2w4wrq4gc unique (beneficiario_id)

    alter table public.dirf_fci_beneficiario 
       add constraint UK_71mg7fukqi71o98jujmxopitg unique (beneficiario_id)

    alter table public.dirf_proc_beneficiario 
       add constraint UK_oq3qek5dp3qtm691fpbjs8d6r unique (beneficiario_id)

    alter table public.dirf_rpde_beneficiario 
       add constraint UK_tfiynegm1oueco686ma5yllne unique (beneficiario_id)

    alter table public.dirf_rra_beneficiario 
       add constraint UK_lr9asm3wo2pwaavm41s1i0nta unique (beneficiario_id)

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

    alter table public.dirf_declarante_beneficiario 
       add constraint FKo481o9dgau622uqvwt2t6l0t8 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_declarante_beneficiario 
       add constraint FKkpqdhwwl56svc985t1j2xmyp1 
       foreign key (declarante_id) 
       references public.dirf_declarante

    alter table public.dirf_fci 
       add constraint FKge3yke8va7xuo551e5puhxlob 
       foreign key (id_dirf) 
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
       add constraint FKp5f9g2y0gduk0erabrcluck0h 
       foreign key (id_dirf) 
       references public.dirf

    alter table public.dirf_proc 
       add constraint FK38h9fa72m1cdiitspwj4mu54f 
       foreign key (id_dirf) 
       references public.dirf

    alter table public.dirf_proc_beneficiario 
       add constraint FK80pt06mxwdkqiurqtvx9ron65 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_proc_beneficiario 
       add constraint FKcq9lm8fu6sgh05bm6v1oxpluf 
       foreign key (proc_id) 
       references public.dirf_proc

    alter table public.dirf_pse_opse 
       add constraint FK5idvowq99ebc0n1xfoj9pcotu 
       foreign key (id_dirf) 
       references public.dirf

    alter table public.dirf_rpde_beneficiario 
       add constraint FK6g6wdp7ptfnn58xpowgu6rwj5 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_rpde_beneficiario 
       add constraint FKby6rv7ofae2ai2w1tlv89c2r2 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.dirf_rra 
       add constraint FKesool5fimsn6vfe98hbwc60xa 
       foreign key (id_dirf) 
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
       add constraint FKtnitccvto66grr77eelh62ys1 
       foreign key (id_dirf) 
       references public.dirf

    alter table public.dirf_scp_beneficiario 
       add constraint FKokjj3wh13lrindl3kpdc45c04 
       foreign key (beneficiario_id) 
       references public.dirf_beneficiario

    alter table public.dirf_scp_beneficiario 
       add constraint FK57dvbb2o4eqkei4r2nyqc1vxw 
       foreign key (scp_id) 
       references public.dirf_scp
