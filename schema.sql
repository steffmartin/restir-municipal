
    create table public.bpfdec (
       id varchar(255) not null,
        alimentado char(1) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nome varchar(60) not null,
        prev_compl char(1) not null,
        primary key (id)
    )

    create table public.bpffci (
       id varchar(255) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nome varchar(60) not null,
        primary key (id)
    )

    create table public.bpfproc (
       id varchar(255) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nome varchar(60) not null,
        primary key (id)
    )

    create table public.bpfrra (
       id varchar(255) not null,
        alimentado char(1) not null,
        cpf varchar(11) not null,
        data_laudo date,
        nat_rra varchar(50),
        nome varchar(60) not null,
        primary key (id)
    )

    create table public.bpfscp (
       id varchar(255) not null,
        cpf varchar(11) not null,
        nome varchar(60) not null,
        participacao float4,
        primary key (id)
    )

    create table public.bpjdec (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.bpjfci (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.bpjproc (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.bpjscp (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        participacao float4,
        primary key (id)
    )

    create table public.brpde (
       id varchar(255) not null,
        bairro_dist varchar(20),
        beneficiario int2 not null,
        cep varchar(10),
        cidade varchar(40),
        cod_pais int2 not null,
        complemento varchar(25),
        cpf_cnp varchar(14),
        dispensa_nif char(1) not null,
        estado varchar(40),
        fone varchar(15),
        logradouro varchar(60),
        nif varchar(30),
        nome varchar(150) not null,
        numero varchar(6),
        pais_sem_nif char(1) not null,
        rel_fonte_benef int2,
        primary key (id)
    )

    create table public.decpf (
       id varchar(255) not null,
        cpf varchar(11) not null,
        cpf_invent varchar(11),
        data_evento date,
        data_obito date,
        espolio_saida_def char(1) not null,
        falecido char(1) not null,
        nome varchar(60) not null,
        nome_invent varchar(60),
        pgto_plano_saude char(1) not null,
        pgto_rend_ext char(1) not null,
        sit_espolio int2,
        socio_ostensivo char(1) not null,
        tipo_evento int2,
        tit_serv_notoriais char(1) not null,
        primary key (id)
    )

    create table public.decpj (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        cpf_resp varchar(11) not null,
        data_evento date,
        decisao_judicial char(1) not null,
        fund_pub_dir_priv char(1) not null,
        fundo_clube_invest char(1) not null,
        natureza int2 not null,
        nome varchar(150) not null,
        pgto_isentas char(1) not null,
        pgto_plano_saude char(1) not null,
        pgto_rend_ext char(1) not null,
        sit_especial char(1) not null,
        socio_ostensivo char(1) not null,
        primary key (id)
    )

    create table public.dirf (
       id varchar(255) not null,
        ano_cal int2 not null,
        ano_ref int2 not null,
        id_leiaute varchar(7) not null,
        num_rec varchar(12),
        retif char(1) not null,
        primary key (id)
    )

    create table public.dtpse (
       id varchar(255) not null,
        cpf varchar(11),
        data_nascimento date,
        nome varchar(60) not null,
        rel_dependencia int2,
        vlr_ano numeric(19, 2) not null,
        primary key (id)
    )

    create table public.fci (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.idrec (
       id varchar(255) not null,
        cod_rec varchar(4) not null,
        primary key (id)
    )

    create table public.inf (
       id varchar(255) not null,
        cpf varchar(11) not null,
        informacoes varchar(500) not null,
        primary key (id)
    )

    create table public.infpa (
       id varchar(255) not null,
        cpf varchar(11),
        data_nascimento date,
        nome varchar(60) not null,
        rel_dependencia int2,
        primary key (id)
    )

    create table public.infpc (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.opse (
       id varchar(255) not null,
        ans varchar(6),
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.proc (
       id varchar(255) not null,
        cpf_cnpj_adv varchar(14),
        id_justica int2 not null,
        nome_adv varchar(150),
        num_proc varchar(20) not null,
        tipo_adv int2,
        vlr_adv numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.qtmeses (
       id varchar(255) not null,
        abr int2 default 0,
        ago int2 default 0,
        dez int2 default 0,
        fev int2 default 0,
        jan int2 default 0,
        jul int2 default 0,
        jun int2 default 0,
        mai int2 default 0,
        mar int2 default 0,
        nov int2 default 0,
        out int2 default 0,
        set int2 default 0,
        primary key (id)
    )

    create table public.rdtpse (
       id varchar(255) not null,
        cpf_cnp varchar(14) not null,
        nome varchar(150) not null,
        vlr_ano_cal numeric(19, 2) default 0,
        vlr_anos_ant numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.reg_val_ano_sem_ret (
       id varchar(255) not null,
        registro varchar(6) not null,
        vlr_ano numeric(19, 2) not null,
        primary key (id)
    )

    create table public.reg_val_mes (
       id varchar(255) not null,
        abr numeric(19, 2) default 0,
        ago numeric(19, 2) default 0,
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
        registro varchar(5) not null,
        set numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.respo (
       id varchar(255) not null,
        cpf varchar(11) not null,
        ddd varchar(2) not null,
        email varchar(50),
        fax varchar(9),
        fone varchar(9) not null,
        nome varchar(60) not null,
        ramal varchar(6),
        primary key (id)
    )

    create table public.rio (
       id varchar(255) not null,
        descricao varchar(60) not null,
        vlr_ano numeric(19, 2) not null,
        primary key (id)
    )

    create table public.rra (
       id varchar(255) not null,
        cpf_cnpj_adv varchar(14),
        id_rra int2 not null,
        nome_adv varchar(150),
        num_proc varchar(20),
        tipo_adv int2,
        vlr_adv numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.rtpse (
       id varchar(255) not null,
        cpf_cnp varchar(14) not null,
        nome varchar(150) not null,
        vlr_ano_cal numeric(19, 2) default 0,
        vlr_anos_ant numeric(19, 2) default 0,
        primary key (id)
    )

    create table public.scp (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.tpse (
       id varchar(255) not null,
        cpf varchar(11) not null,
        nome varchar(60) not null,
        vlr_ano numeric(19, 2) not null,
        primary key (id)
    )

    create table public.vpeim (
       id varchar(255) not null,
        cnpj varchar(14) not null,
        nome varchar(150) not null,
        primary key (id)
    )

    create table public.vrpde (
       id varchar(255) not null,
        cod_rec varchar(4) not null,
        data_pgto date not null,
        forma_tribut int2 not null,
        tipo_rendimento int2 not null,
        vlr_rendimento numeric(19, 2) not null,
        vlr_retido numeric(19, 2) default 0,
        primary key (id)
    )
