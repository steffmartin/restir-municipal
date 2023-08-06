
    create table public.bpfdec (
       linha_bpfdec int4 not null,
        alimentado char(1) not null,
        cod_receita varchar(4) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nome varchar(60) not null,
        prev_compl char(1) not null,
        dirf_id int4 not null,
        primary key (dirf_id, linha_bpfdec)
    )

    create table public.bpffci (
       linha_bpffci int4 not null,
        cod_receita varchar(4) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nome varchar(60) not null,
        dirf_id serial not null,
        linha_fci int4 not null,
        primary key (dirf_id, linha_fci, linha_bpffci)
    )

    create table public.bpfproc (
       linha_bpfproc int4 not null,
        cod_receita varchar(4) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nome varchar(60) not null,
        dirf_id serial not null,
        linha_proc int4 not null,
        primary key (linha_bpfproc, dirf_id, linha_proc)
    )

    create table public.bpfrra (
       linha_bpfrra int4 not null,
        alimentado char(1) not null,
        cod_receita varchar(4) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nat_rra varchar(50),
        nome varchar(60) not null,
        dirf_id serial not null,
        linha_rra int4 not null,
        primary key (linha_bpfrra, dirf_id, linha_rra)
    )

    create table public.bpfscp (
       linha_bpfscp int4 not null,
        cpf varchar(11) not null,
        nome varchar(60) not null,
        participacao float4,
        dirf_id serial not null,
        linha_scp int4 not null,
        primary key (linha_bpfscp, dirf_id, linha_scp)
    )

    create table public.bpjdec (
       linha_bpjdec int4 not null,
        cnpj varchar(14) not null,
        cod_receita varchar(4) not null,
        nome varchar(150) not null,
        dirf_id int4 not null,
        primary key (dirf_id, linha_bpjdec)
    )

    create table public.bpjfci (
       linha_bpjfci int4 not null,
        cnpj varchar(14) not null,
        cod_receita varchar(4) not null,
        nome varchar(150) not null,
        dirf_id serial not null,
        linha_fci int4 not null,
        primary key (dirf_id, linha_fci, linha_bpjfci)
    )

    create table public.bpjproc (
       linha_bpjproc int4 not null,
        cnpj varchar(14) not null,
        cod_receita varchar(4) not null,
        nome varchar(150) not null,
        dirf_id serial not null,
        linha_proc int4 not null,
        primary key (linha_bpjproc, dirf_id, linha_proc)
    )

    create table public.bpjscp (
       linha_bpjscp int4 not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        participacao float4,
        dirf_id serial not null,
        linha_scp int4 not null,
        primary key (linha_bpjscp, dirf_id, linha_scp)
    )

    create table public.brpde (
       linha_brpde int4 not null,
        bairro_dist varchar(20),
        beneficiario int2 not null,
        cep varchar(10),
        cidade varchar(40),
        cod_pais int2 not null,
        complemento varchar(25),
        cpf_cnp varchar(14),
        estado varchar(40),
        fone varchar(15),
        logradouro varchar(60),
        nif varchar(30),
        nif_dispensado char(1) not null,
        nif_opcional char(1) not null,
        nome varchar(150) not null,
        numero varchar(6),
        rel_fonte_benef int2,
        dirf_id serial not null,
        primary key (dirf_id, linha_brpde)
    )

    create table public.declarante (
       cod_registro varchar(5) not null,
        dirf_id int4 not null,
        cpf_cnpj_declarante varchar(14) not null,
        linha_dec int4 default 3,
        nome_declarante varchar(150) not null,
        pgto_plano_saude char(1) not null,
        pgto_rend_ext char(1) not null,
        socio_ostensivo char(1) not null,
        primary key (dirf_id)
    )

    create table public.decpf (
       cpf_invent varchar(11),
        data_espolio_saida_def date,
        data_obito date,
        espolio_saida_def char(1) not null,
        falecido char(1) not null,
        nome_invent varchar(60),
        sit_espolio int2,
        tipo_evento int2,
        tit_serv_notoriais char(1) not null,
        dirf_id int4 not null,
        primary key (dirf_id)
    )

    create table public.decpj (
       cpf_resp varchar(11) not null,
        data_evento date,
        decisao_judicial char(1) not null,
        fund_pub_dir_priv char(1) not null,
        fundo_clube_invest char(1) not null,
        natureza int2 not null,
        pgto_isentas char(1) not null,
        sit_especial char(1) not null,
        dirf_id int4 not null,
        primary key (dirf_id)
    )

    create table public.dirf (
       dirf_id  serial not null,
        ano_cal int2 not null,
        ano_ref int2 not null,
        id_leiaute varchar(7) not null,
        linha_dirf int4 default 1,
        linha_fim_dirf int4,
        num_rec varchar(12),
        retif char(1) not null,
        primary key (dirf_id)
    )

    create table public.dtpse (
       linha_dtpse int4 not null,
        cpf varchar(11),
        data_nascimento date,
        nome varchar(60) not null,
        rel_dependencia int2,
        vlr_ano numeric(19, 2) not null,
        linha_tpse int4 not null,
        dirf_id serial not null,
        linha_opse int4 not null,
        primary key (linha_dtpse, linha_tpse, dirf_id, linha_opse)
    )

    create table public.fci (
       linha_fci int4 not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        dirf_id serial not null,
        primary key (dirf_id, linha_fci)
    )

    create table public.inf (
       linha_inf int4 not null,
        cpf varchar(11) not null,
        informacoes varchar(500) not null,
        dirf_id serial not null,
        primary key (dirf_id, linha_inf)
    )

    create table public.infpa (
       linha_infpa int4 not null,
        cpf varchar(11),
        data_nascimento date,
        nome varchar(60) not null,
        rel_dependencia int2,
        primary key (linha_infpa)
    )

    create table public.infpc (
       linha_infpc int4 not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        dirf_id int4 not null,
        linha_bpfdec int4 not null,
        primary key (dirf_id, linha_bpfdec, linha_infpc)
    )

    create table public.opse (
       linha_opse int4 not null,
        ans varchar(6),
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        dirf_id serial not null,
        primary key (dirf_id, linha_opse)
    )

    create table public.proc (
       linha_proc int4 not null,
        cpf_cnpj_adv varchar(14),
        id_justica int2 not null,
        nome_adv varchar(150),
        num_proc varchar(20) not null,
        tipo_adv int2,
        vlr_adv numeric(19, 2) default 0,
        dirf_id serial not null,
        primary key (dirf_id, linha_proc)
    )

    create table public.qtmeses (
       dirf_id int4 not null,
        linha_bpfrra int4 not null,
        linha_rra int4 not null,
        abr int2 default 0,
        ago int2 default 0,
        dez int2 default 0,
        fev int2 default 0,
        jan int2 default 0,
        jul int2 default 0,
        jun int2 default 0,
        linha_qtmeses int4,
        mai int2 default 0,
        mar int2 default 0,
        nov int2 default 0,
        out int2 default 0,
        set int2 default 0,
        primary key (dirf_id, linha_bpfrra, linha_rra)
    )

    create table public.rdtpse (
       linha_rdtpse int4 not null,
        cpf_cnp varchar(14) not null,
        nome varchar(150) not null,
        vlr_ano_cal numeric(19, 2) default 0,
        vlr_anos_ant numeric(19, 2) default 0,
        linha_rtpse int4 not null,
        linha_tpse int4 not null,
        dirf_id serial not null,
        linha_opse int4 not null,
        primary key (linha_rdtpse, linha_rtpse, linha_tpse, dirf_id, linha_opse)
    )

    create table public.reg_val_ano_sem_ret (
       linha_registro_ano int4 not null,
        cod_registro varchar(6) not null,
        vlr_ano numeric(19, 2) not null,
        primary key (linha_registro_ano)
    )

    create table public.reg_val_mes (
       linha_registro_mes int4 not null,
        abr numeric(19, 2) default 0,
        ago numeric(19, 2) default 0,
        cod_registro varchar(5) not null,
        dec_ter numeric(19, 2) default 0,
        dez numeric(19, 2) default 0,
        fev numeric(19, 2) default 0,
        jan numeric(19, 2) default 0,
        jul numeric(19, 2) default 0,
        jun numeric(19, 2) default 0,
        mai numeric(19, 2) default 0,
        mar numeric(19, 2) default 0,
        nov numeric(19, 2) default 0,
        out numeric(19, 2) default 0,
        set numeric(19, 2) default 0,
        primary key (linha_registro_mes)
    )

    create table public.respo (
       dirf_id int4 not null,
        cpf varchar(11) not null,
        ddd varchar(2) not null,
        email varchar(50),
        fax varchar(9),
        fone varchar(9) not null,
        linha_respo int4 default 2,
        nome varchar(60) not null,
        ramal varchar(6),
        primary key (dirf_id)
    )

    create table public.rio (
       linha_rio int4 not null,
        descricao varchar(60) not null,
        vlr_ano numeric(19, 2) not null,
        primary key (linha_rio)
    )

    create table public.rra (
       linha_rra int4 not null,
        cpf_cnpj_adv varchar(14),
        nome_adv varchar(150),
        num_proc varchar(20),
        tipo_adv int2,
        tipo_rra int2 not null,
        vlr_adv numeric(19, 2) default 0,
        dirf_id serial not null,
        primary key (dirf_id, linha_rra)
    )

    create table public.rtpse (
       linha_rtpse int4 not null,
        cpf_cnp varchar(14) not null,
        nome varchar(150) not null,
        vlr_ano_cal numeric(19, 2) default 0,
        vlr_anos_ant numeric(19, 2) default 0,
        linha_tpse int4 not null,
        dirf_id serial not null,
        linha_opse int4 not null,
        primary key (linha_rtpse, linha_tpse, dirf_id, linha_opse)
    )

    create table public.scp (
       linha_scp int4 not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        dirf_id serial not null,
        primary key (dirf_id, linha_scp)
    )

    create table public.tpse (
       linha_tpse int4 not null,
        cpf varchar(11) not null,
        nome varchar(60) not null,
        vlr_ano numeric(19, 2) not null,
        dirf_id serial not null,
        linha_opse int4 not null,
        primary key (linha_tpse, dirf_id, linha_opse)
    )

    create table public.vpeim (
       linha_vpeim int4 not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        dirf_id int4 not null,
        primary key (dirf_id, linha_vpeim)
    )

    create table public.vrpde (
       linha_vrpde int4 not null,
        cod_rec varchar(4) not null,
        data_pgto date not null,
        forma_tribut int2 not null,
        tipo_rendimento int2 not null,
        vlr_rendimento numeric(19, 2) not null,
        vlr_retido numeric(19, 2) default 0,
        dirf_id serial not null,
        linha_brpde int4 not null,
        primary key (dirf_id, linha_brpde, linha_vrpde)
    )

    alter table public.bpfdec 
       add constraint FKnc2y4d2wp7cgp7pjwv0oc0pkp 
       foreign key (dirf_id) 
       references public.declarante

    alter table public.bpffci 
       add constraint FK7la0a4060qr71817opmyxv4c5 
       foreign key (dirf_id, linha_fci) 
       references public.fci

    alter table public.bpfproc 
       add constraint FKdo6q3cuh5p3jmchjsjd86374d 
       foreign key (dirf_id, linha_proc) 
       references public.proc

    alter table public.bpfrra 
       add constraint FK274r5hnu8qb08r1iheci55xvr 
       foreign key (dirf_id, linha_rra) 
       references public.rra

    alter table public.bpfscp 
       add constraint FK7khoapdo39hi4i17hf1lhop8e 
       foreign key (dirf_id, linha_scp) 
       references public.scp

    alter table public.bpjdec 
       add constraint FKkf4ygdh9vdyo9y4x2x4jt7w0x 
       foreign key (dirf_id) 
       references public.declarante

    alter table public.bpjfci 
       add constraint FKfr2e306eb3bg7r1sicb27xqgt 
       foreign key (dirf_id, linha_fci) 
       references public.fci

    alter table public.bpjproc 
       add constraint FKd4g3ci21jrhcw12qbnng14kgo 
       foreign key (dirf_id, linha_proc) 
       references public.proc

    alter table public.bpjscp 
       add constraint FKpcno4vopx4nths1kqfvjon84u 
       foreign key (dirf_id, linha_scp) 
       references public.scp

    alter table public.brpde 
       add constraint FKhqof31wbsoq7gbb60v42sd10c 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.declarante 
       add constraint FK5wt3y9jw0f6y9oalstnanyl7w 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.decpf 
       add constraint FKq5ximr0p82arnnme24yu50o89 
       foreign key (dirf_id) 
       references public.declarante

    alter table public.decpj 
       add constraint FKf35ol1dpngm6knx7515ogdct8 
       foreign key (dirf_id) 
       references public.declarante

    alter table public.dtpse 
       add constraint FKqq72gynmwfvh76r4uesllt3w6 
       foreign key (linha_tpse, dirf_id, linha_opse) 
       references public.tpse

    alter table public.fci 
       add constraint FK52jyrw0ipole8pq9xi6lbvtoh 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.inf 
       add constraint FKqoa9vbndjd9x8nehm75d7wd9l 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.infpc 
       add constraint FKt8nrlj4fxafcsvtmusdw6kxlu 
       foreign key (dirf_id, linha_bpfdec) 
       references public.bpfdec

    alter table public.opse 
       add constraint FKnly9q20o49hwa993nfoeb5e0e 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.proc 
       add constraint FK2dmww8cgm03ephdb8jp6fm7qu 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.qtmeses 
       add constraint FKa3twkgc0okykat5casj6c4cn6 
       foreign key (dirf_id, linha_bpfrra, linha_rra) 
       references public.bpfrra

    alter table public.rdtpse 
       add constraint FK764yjoyb2240qtlqoe9toxxqm 
       foreign key (linha_rtpse, linha_tpse, dirf_id, linha_opse) 
       references public.rtpse

    alter table public.respo 
       add constraint FKp1yn8dexkc92klqu641wamj71 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.rra 
       add constraint FK6m7aa1215cvr2v32t1bwenhnu 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.rtpse 
       add constraint FK30567sw1rsgppoxovehe73lt9 
       foreign key (linha_tpse, dirf_id, linha_opse) 
       references public.tpse

    alter table public.scp 
       add constraint FKb3wbn643m7400crpm1xld1apk 
       foreign key (dirf_id) 
       references public.dirf

    alter table public.tpse 
       add constraint FK2xwwu0e58kfbylwbw5nhapllu 
       foreign key (dirf_id, linha_opse) 
       references public.opse

    alter table public.vpeim 
       add constraint FKe2uo9ebq1bavjojbomxe074px 
       foreign key (dirf_id) 
       references public.decpj

    alter table public.vrpde 
       add constraint FKot5ryw65r8562n1rp97yjwrxu 
       foreign key (dirf_id, linha_brpde) 
       references public.brpde
