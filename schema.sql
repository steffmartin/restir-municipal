
    create table public.au_cte (
       id_cte int8 not null,
        aliquota_icms float4,
        aliquota_icms_ret float4,
        autorizado int4,
        bairro_destinatario varchar(255),
        bairro_emitente varchar(255),
        bairro_expedidor varchar(255),
        bairro_recebedor varchar(255),
        bairro_remetente varchar(255),
        bairro_tomador varchar(255),
        base_icms float4,
        base_icms_ret float4,
        cancelada int4,
        cep_destinatario varchar(255),
        cep_emitente varchar(255),
        cep_expedidor varchar(255),
        cep_recebedor varchar(255),
        cep_remetente varchar(255),
        cep_tomador varchar(255),
        cfop int4,
        chave varchar(255),
        cnpj_destinatario varchar(255),
        cnpj_emitente varchar(255),
        cnpj_expedidor varchar(255),
        cnpj_recebedor varchar(255),
        cnpj_remetente varchar(255),
        cnpj_tomador varchar(255),
        compl_destinatario varchar(255),
        compl_emitente varchar(255),
        compl_expedidor varchar(255),
        compl_recebedor varchar(255),
        compl_remetente varchar(255),
        compl_tomador varchar(255),
        cpf_destinatario varchar(255),
        cpf_expedidor varchar(255),
        cpf_recebedor varchar(255),
        cpf_remetente varchar(255),
        cpf_tomador varchar(255),
        cst_icms varchar(255),
        data_autorizacao date,
        data_cadastro date,
        data_emissao date,
        data_final date,
        data_inicial date,
        data_prevista_ent date,
        denegada int4,
        endereco_destinatario varchar(255),
        endereco_emitente varchar(255),
        endereco_expedidor varchar(255),
        endereco_recebedor varchar(255),
        endereco_remetente varchar(255),
        endereco_tomador varchar(255),
        forma_pagamento int4,
        hora_autorizacao varchar(255),
        ibge_destinatario int4,
        ibge_emitente int4,
        ibge_expedidor int4,
        ibge_recebedor int4,
        ibge_remetente int4,
        ibge_tomador int4,
        id_empresa int8,
        ie_destinatario varchar(255),
        ie_emitente varchar(255),
        ie_expedidor varchar(255),
        ie_recebedor varchar(255),
        ie_remetente varchar(255),
        ie_tomador varchar(255),
        ind_simples int4,
        lota int4,
        modal varchar(255),
        natureza_operacao varchar(255),
        nome_destinatario varchar(255),
        nome_emitente varchar(255),
        nome_expedidor varchar(255),
        nome_fant_emitente varchar(255),
        nome_fant_remetente varchar(255),
        nome_fant_tomador varchar(255),
        nome_recebedor varchar(255),
        nome_remetente varchar(255),
        nome_tomador varchar(255),
        numero_cte int8,
        numero_destinatario varchar(255),
        numero_emitente varchar(255),
        numero_expedidor varchar(255),
        numero_recebedor varchar(255),
        numero_remetente varchar(255),
        numero_tomador varchar(255),
        outras_carac varchar(255),
        prod_predominante varchar(255),
        protocolo varchar(255),
        reducao_base_icms float4,
        rntrc varchar(255),
        serie varchar(255),
        tel_destinatario varchar(255),
        tel_expedidor varchar(255),
        tel_recebedor varchar(255),
        tel_remetente varchar(255),
        tel_tomador varchar(255),
        tipo_cte int4,
        tipo_servico int4,
        tomador int4,
        uf_destinatario varchar(255),
        uf_emitente varchar(255),
        uf_expedidor varchar(255),
        uf_fim varchar(255),
        uf_inicio varchar(255),
        uf_recebedor varchar(255),
        uf_remetente varchar(255),
        uf_tomador varchar(255),
        valor_carga float4,
        valor_credito float4,
        valor_icms float4,
        valor_icms_ret float4,
        valor_receber float4,
        valor_total float4,
        valor_tributos float4,
        primary key (id_cte)
    )

    create table public.au_cte_nfes (
       id_cte_nfes int8 not null,
        chave_nfe varchar(255),
        id_cte int8,
        primary key (id_cte_nfes)
    )

    create table public.au_empresa (
       id_empresa int8 not null,
        bairro varchar(255),
        cep varchar(255),
        codigo_ibge int4,
        complemento varchar(255),
        email varchar(255),
        fax varchar(255),
        logradouro varchar(255),
        municipio varchar(255),
        nome_fantasia varchar(255),
        nome_pasta varchar(255),
        numero varchar(255),
        numero_cgc_mf varchar(255),
        numero_insc_estadual varchar(255),
        numero_insc_municipal varchar(255),
        razao_social varchar(255),
        responsavel varchar(255),
        telefone varchar(255),
        uf varchar(255),
        primary key (id_empresa)
    )

    create table public.au_extrato_saldo (
       id_au_extrato_saldo int8 not null,
        codigo_produto varchar(255),
        descricao varchar(255),
        dtcontagem date,
        ean varchar(255),
        ncm varchar(255),
        qtdest numeric(19, 2),
        un varchar(255),
        valor_unitario numeric(19, 2),
        id_empresa int8,
        primary key (id_au_extrato_saldo)
    )

    create table public.au_nfce (
       id_nfce int8 not null,
        autorizado int4,
        bairro_destinatario varchar(255),
        bairro_emitente varchar(255),
        base_icms float4,
        base_icms_st float4,
        cancelada int4,
        cep_destinatario int4,
        cep_emitente int4,
        chave varchar(255),
        cnpj_destinatario varchar(255),
        cnpj_emitente varchar(255),
        cnpj_transportadora varchar(255),
        cpf_destinatario varchar(255),
        cpf_transportadora varchar(255),
        crt int4,
        data_autorizacao date,
        data_cadastro date,
        data_emissao date,
        data_final date,
        data_inicial date,
        data_saida_entrada date,
        denegada int4,
        end_transportadora varchar(255),
        endereco_destinatario varchar(255),
        endereco_emitente varchar(255),
        especie_volumes varchar(255),
        hora_autorizacao varchar(255),
        hora_saida_entrada varchar(255),
        ibge_destinatario int4,
        ibge_emitente int4,
        id_empresa int8,
        ie_destinatario varchar(255),
        ie_emitente varchar(255),
        ie_subst_trib varchar(255),
        ie_transportadora varchar(255),
        informacoes_complementares varchar(255),
        informacoes_fisco varchar(255),
        marca_volumes varchar(255),
        mod_frete int4,
        modelo varchar(255),
        municipio_destinatario varchar(255),
        municipio_emitente varchar(255),
        municipio_transportadora varchar(255),
        natureza_operacao varchar(255),
        nome_destinatario varchar(255),
        nome_emitente varchar(255),
        nome_fantasia_emitente varchar(255),
        nome_transportadora varchar(255),
        numeracao_volumes varchar(255),
        numero_destinatario varchar(255),
        numero_emitente varchar(255),
        peso_bruto float4,
        peso_liquido float4,
        placa varchar(255),
        protocolo varchar(255),
        quant_volumes float4,
        rntc varchar(255),
        serie varchar(255),
        tipo_operacao int4,
        uf_destinatario varchar(255),
        uf_emitente varchar(255),
        uf_placa varchar(255),
        uf_transportadora varchar(255),
        valor_base_irrf_retido float4,
        valor_cofins float4,
        valor_cofins_retido float4,
        valor_csll_retido float4,
        valor_desconto float4,
        valor_frete float4,
        valor_icms float4,
        valor_icms_st float4,
        valor_ii float4,
        valor_ipi float4,
        valor_irrf_retido float4,
        valor_outras float4,
        valor_pis float4,
        valor_pis_retido float4,
        valor_produtos float4,
        valor_seguro float4,
        valor_total float4,
        numero_nfce int8,
        primary key (id_nfce)
    )

    create table public.au_nfce_item (
       item int8 not null,
        aliq_cofins_st float4,
        aliq_icms_fcp_uf_dest float4,
        aliq_inter_uf_dest float4,
        aliq_interestadual_ufs float4,
        aliq_pis_st float4,
        aliquota_cofins float4,
        aliquota_credito_sn float4,
        aliquota_fcp float4,
        aliquota_icms float4,
        aliquota_icms_st float4,
        aliquota_ipi float4,
        aliquota_pis float4,
        aliquota_st_fcp float4,
        base_cofins float4,
        base_fcp float4,
        base_icms float4,
        base_icms_st float4,
        base_icms_st_retido float4,
        base_ipi float4,
        base_pis float4,
        base_st_fcp float4,
        bc_cofins_st float4,
        bc_fcp_uf_dest float4,
        bc_icms_uf_dest float4,
        bc_pis_st float4,
        cfop int4,
        chave varchar(255),
        cod_list_serv varchar(255),
        cod_mun_fg varchar(255),
        cod_mun_serv varchar(255),
        codigo_barras varchar(255),
        codigo_produto varchar(255),
        cst_cofins varchar(255),
        cst_icms varchar(255),
        cst_ipi varchar(255),
        cst_pis varchar(255),
        descricao varchar(255),
        fci varchar(255),
        id_empresa int8,
        ind_incentivo varchar(255),
        ind_iss varchar(255),
        mva float4,
        ncm varchar(255),
        origem_mercadoria int4,
        perc_prov_partilha float4,
        quantidade float4,
        reducao_base_icms float4,
        reducao_base_icms_st float4,
        unidade_medida varchar(255),
        valor_aliquota_issqn float4,
        valor_base_issqn float4,
        valor_cofins float4,
        valor_credito_sn float4,
        valor_desconto float4,
        valor_fcp float4,
        valor_frete float4,
        valor_icms float4,
        valor_icms_fcp float4,
        valor_icms_st float4,
        valor_icms_st_retido float4,
        valor_ipi float4,
        valor_issqn float4,
        valor_outras_despesas float4,
        valor_pis float4,
        valor_seguro float4,
        valor_st_fcp float4,
        valor_total float4,
        valor_unitario float4,
        vr_cofins_st float4,
        vr_icms_interestadual_uf_dest float4,
        vr_icms_interstadual_uf_rem float4,
        vr_pis_st float4,
        id_nfce int8 not null,
        primary key (item, id_nfce)
    )

    create table public.au_nfe (
       id_nfe int8 not null,
        autorizado int4,
        bairro_destinatario varchar(255),
        bairro_emitente varchar(255),
        base_icms float4,
        base_icms_st float4,
        cancelada int4,
        cep_destinatario int4,
        cep_emitente int4,
        chave varchar(255),
        cnpj_destinatario varchar(255),
        cnpj_emitente varchar(255),
        cnpj_transportadora varchar(255),
        cpf_destinatario varchar(255),
        cpf_transportadora varchar(255),
        crt int4,
        data_autorizacao date,
        data_cadastro date,
        data_emissao date,
        data_final date,
        data_inicial date,
        data_saida_entrada date,
        denegada int4,
        end_transportadora varchar(255),
        endereco_destinatario varchar(255),
        endereco_emitente varchar(255),
        especie_volumes varchar(255),
        hora_autorizacao varchar(255),
        hora_saida_entrada varchar(255),
        ibge_destinatario int4,
        ibge_emitente int4,
        id_empresa int8,
        ie_destinatario varchar(255),
        ie_emitente varchar(255),
        ie_subst_trib varchar(255),
        ie_transportadora varchar(255),
        informacoes_complementares varchar(255),
        informacoes_fisco varchar(255),
        marca_volumes varchar(255),
        mod_frete int4,
        modelo varchar(255),
        municipio_destinatario varchar(255),
        municipio_emitente varchar(255),
        municipio_transportadora varchar(255),
        natureza_operacao varchar(255),
        nome_destinatario varchar(255),
        nome_emitente varchar(255),
        nome_fantasia_emitente varchar(255),
        nome_transportadora varchar(255),
        numeracao_volumes varchar(255),
        numero_destinatario varchar(255),
        numero_emitente varchar(255),
        peso_bruto float4,
        peso_liquido float4,
        placa varchar(255),
        protocolo varchar(255),
        quant_volumes float4,
        rntc varchar(255),
        serie varchar(255),
        tipo_operacao int4,
        uf_destinatario varchar(255),
        uf_emitente varchar(255),
        uf_placa varchar(255),
        uf_transportadora varchar(255),
        valor_base_irrf_retido float4,
        valor_cofins float4,
        valor_cofins_retido float4,
        valor_csll_retido float4,
        valor_desconto float4,
        valor_frete float4,
        valor_icms float4,
        valor_icms_st float4,
        valor_ii float4,
        valor_ipi float4,
        valor_irrf_retido float4,
        valor_outras float4,
        valor_pis float4,
        valor_pis_retido float4,
        valor_produtos float4,
        valor_seguro float4,
        valor_total float4,
        numero_nfe int8,
        primary key (id_nfe)
    )

    create table public.au_nfe_item (
       item int8 not null,
        aliq_cofins_st float4,
        aliq_icms_fcp_uf_dest float4,
        aliq_inter_uf_dest float4,
        aliq_interestadual_ufs float4,
        aliq_pis_st float4,
        aliquota_cofins float4,
        aliquota_credito_sn float4,
        aliquota_fcp float4,
        aliquota_icms float4,
        aliquota_icms_st float4,
        aliquota_ipi float4,
        aliquota_pis float4,
        aliquota_st_fcp float4,
        base_cofins float4,
        base_fcp float4,
        base_icms float4,
        base_icms_st float4,
        base_icms_st_retido float4,
        base_ipi float4,
        base_pis float4,
        base_st_fcp float4,
        bc_cofins_st float4,
        bc_fcp_uf_dest float4,
        bc_icms_uf_dest float4,
        bc_pis_st float4,
        cfop int4,
        chave varchar(255),
        cod_list_serv varchar(255),
        cod_mun_fg varchar(255),
        cod_mun_serv varchar(255),
        codigo_barras varchar(255),
        codigo_produto varchar(255),
        cst_cofins varchar(255),
        cst_icms varchar(255),
        cst_ipi varchar(255),
        cst_pis varchar(255),
        descricao varchar(255),
        fci varchar(255),
        id_empresa int8,
        ind_incentivo varchar(255),
        ind_iss varchar(255),
        mva float4,
        ncm varchar(255),
        origem_mercadoria int4,
        perc_prov_partilha float4,
        quantidade float4,
        reducao_base_icms float4,
        reducao_base_icms_st float4,
        unidade_medida varchar(255),
        valor_aliquota_issqn float4,
        valor_base_issqn float4,
        valor_cofins float4,
        valor_credito_sn float4,
        valor_desconto float4,
        valor_fcp float4,
        valor_frete float4,
        valor_icms float4,
        valor_icms_fcp float4,
        valor_icms_st float4,
        valor_icms_st_retido float4,
        valor_ipi float4,
        valor_issqn float4,
        valor_outras_despesas float4,
        valor_pis float4,
        valor_seguro float4,
        valor_st_fcp float4,
        valor_total float4,
        valor_unitario float4,
        vr_cofins_st float4,
        vr_icms_interestadual_uf_dest float4,
        vr_icms_interstadual_uf_rem float4,
        vr_pis_st float4,
        id_nfe int8 not null,
        primary key (item, id_nfe)
    )

    create table public.au_pauta_comb_registros (
       id_pauta_comb_registros int8 not null,
        aehc float8,
        diesel_s10 float8,
        oleo_diesel float8,
        gac float8,
        gap float8,
        glp float8 not null,
        glp_p13 float8,
        gni float8 not null,
        gnv float8 not null,
        oleo_comb_kg float8 not null,
        oleo_comb_lt float8 not null,
        qav float8 not null,
        uf varchar(255),
        id_pauta_combustiveis int8,
        primary key (id_pauta_comb_registros)
    )

    create table public.au_pauta_combustiveis (
       id_pauta_combustiveis int8 not null,
        data_final date,
        data_inicial date,
        primary key (id_pauta_combustiveis)
    )

    create table public.au_scontabil_lote (
       id_empresa int8 not null,
        lote int8 not null,
        data_importacao timestamp,
        importado int4,
        nome_arquivo varchar(255),
        quantidade_linhas int4,
        referencia varchar(255),
        primary key (id_empresa, lote)
    )

    create table public.au_scontabil_registros (
       id_empresa int8 not null,
        lote int8 not null,
        conteudo_linha varchar(255),
        numero_linha int4 not null,
        primary key (id_empresa, lote, numero_linha)
    )

    create table public.au_secf_lote (
       id_empresa int8 not null,
        lote int8 not null,
        data_importacao timestamp,
        importado int4,
        nome_arquivo varchar(255),
        quantidade_linhas int4,
        referencia varchar(255),
        primary key (id_empresa, lote)
    )

    create table public.au_secf_registros (
       id_empresa int8 not null,
        lote int8 not null,
        conteudo_linha varchar(255),
        numero_linha int4 not null,
        primary key (id_empresa, lote, numero_linha)
    )

    create table public.au_sped_contrib_registros (
       id_empresa int8 not null,
        lote int8 not null,
        conteudo_linha varchar(255),
        numero_linha int4 not null,
        primary key (id_empresa, lote, numero_linha)
    )

    create table public.au_sped_contribuicoes_lote (
       id_empresa int8 not null,
        lote int8 not null,
        data_importacao timestamp,
        importado int4,
        nome_arquivo varchar(255),
        quantidade_linhas int4,
        referencia varchar(255),
        primary key (id_empresa, lote)
    )

    create table public.au_sped_fiscal_lote (
       id_empresa int8 not null,
        lote int8 not null,
        data_importacao timestamp,
        importado int4,
        nome_arquivo varchar(255),
        quantidade_linhas int4,
        referencia varchar(255),
        primary key (id_empresa, lote)
    )

    create table public.au_sped_fiscal_registros (
       id_empresa int8 not null,
        lote int8 not null,
        conteudo_linha varchar(255),
        numero_linha int4 not null,
        primary key (id_empresa, lote, numero_linha)
    )

    create table public.cad_cidade_ibge (
       codigo_ibge int4 not null,
        nome_cidade varchar(255),
        uf varchar(255),
        primary key (codigo_ibge)
    )

    create table public.cod_cigarro (
       cod_prod_ent varchar(255) not null,
        cod_prod_sai varchar(255),
        id_empresa int8 not null,
        primary key (cod_prod_ent, id_empresa)
    )

    create table public.editor_parametros_ajus_comb_uf (
       combustivel varchar(255) not null,
        data_final date not null,
        data_inicial date not null,
        registro varchar(255) not null,
        uf varchar(255) not null,
        codigo_ajuste varchar(255),
        primary key (combustivel, data_final, data_inicial, registro, uf)
    )

    create table public.editor_parametros_alq_comb_uf (
       combustivel varchar(255) not null,
        data_final date not null,
        data_inicial date not null,
        uf varchar(255) not null,
        aliquota float8 not null,
        primary key (combustivel, data_final, data_inicial, uf)
    )

    create table public.editor_parametros_globais (
       chave varchar(255) not null,
        descricao varchar(255),
        instrucoes varchar(255),
        valor varchar(255),
        primary key (chave)
    )

    create table public.editor_parametros_itens_comb (
       cod_item varchar(255) not null,
        id_empresa int8 not null,
        cod_comb_anp varchar(255),
        combustivel varchar(255),
        primary key (cod_item, id_empresa)
    )

    create table public.editor_parametros_lim_exc_icms (
       id_empresa int8 not null,
        mes_ano date not null,
        limite_icms_exc numeric(19, 2),
        primary key (id_empresa, mes_ano)
    )

    create table public.editor_pauta_combustiveis (
       id int8 not null,
        aliquota numeric(19, 2),
        base numeric(19, 2),
        combustivel varchar(255),
        data_final date,
        data_inicial date,
        uf varchar(255),
        valor numeric(19, 2),
        primary key (id)
    )

    create table public.gera_c185_etanol (
       chave varchar(255) not null,
        id_empresa int8 not null,
        num_item int8 not null,
        cfop int4,
        cod_item varchar(255),
        cod_mod_rest_compl varchar(255),
        cst_icms varchar(255),
        data_emissao date,
        numero_nfce int8,
        quant_conv numeric(19, 2),
        unid varchar(255),
        vl_unit_conv numeric(19, 2),
        vl_unit_fcp_icms_st_estoque_conv numeric(19, 2),
        vl_unit_fcp_st_conv_compl numeric(19, 2),
        vl_unit_fcp_st_conv_rest numeric(19, 2),
        vl_unit_icms_na_operacao_conv numeric(19, 2),
        vl_unit_icms_op_conv numeric(19, 2),
        vl_unit_icms_op_estoque_conv numeric(19, 2),
        vl_unit_icms_st_conv_compl numeric(19, 2),
        vl_unit_icms_st_conv_rest numeric(19, 2),
        vl_unit_icms_st_estoque_conv numeric(19, 2),
        primary key (chave, id_empresa, num_item)
    )

    create table public.gera_c185_gas_comum_adt (
       chave varchar(255) not null,
        id_empresa int8 not null,
        num_item int8 not null,
        cfop int4,
        cod_item varchar(255),
        cod_mod_rest_compl varchar(255),
        cst_icms varchar(255),
        data_emissao date,
        numero_nfce int8,
        quant_conv numeric(19, 2),
        unid varchar(255),
        vl_unit_conv numeric(19, 2),
        vl_unit_fcp_icms_st_estoque_conv numeric(19, 2),
        vl_unit_fcp_st_conv_compl numeric(19, 2),
        vl_unit_fcp_st_conv_rest numeric(19, 2),
        vl_unit_icms_na_operacao_conv numeric(19, 2),
        vl_unit_icms_op_conv numeric(19, 2),
        vl_unit_icms_op_estoque_conv numeric(19, 2),
        vl_unit_icms_st_conv_compl numeric(19, 2),
        vl_unit_icms_st_conv_rest numeric(19, 2),
        vl_unit_icms_st_estoque_conv numeric(19, 2),
        primary key (chave, id_empresa, num_item)
    )

    create table public.gera_c185_s10 (
       chave varchar(255) not null,
        id_empresa int8 not null,
        num_item int8 not null,
        cfop int4,
        cod_item varchar(255),
        cod_mod_rest_compl varchar(255),
        cst_icms varchar(255),
        data_emissao date,
        numero_nfce int8,
        quant_conv numeric(19, 2),
        unid varchar(255),
        vl_unit_conv numeric(19, 2),
        vl_unit_fcp_icms_st_estoque_conv numeric(19, 2),
        vl_unit_fcp_st_conv_compl numeric(19, 2),
        vl_unit_fcp_st_conv_rest numeric(19, 2),
        vl_unit_icms_na_operacao_conv numeric(19, 2),
        vl_unit_icms_op_conv numeric(19, 2),
        vl_unit_icms_op_estoque_conv numeric(19, 2),
        vl_unit_icms_st_conv_compl numeric(19, 2),
        vl_unit_icms_st_conv_rest numeric(19, 2),
        vl_unit_icms_st_estoque_conv numeric(19, 2),
        primary key (chave, id_empresa, num_item)
    )

    create table public.gera_c185_s500 (
       chave varchar(255) not null,
        id_empresa int8 not null,
        num_item int8 not null,
        cfop int4,
        cod_item varchar(255),
        cod_mod_rest_compl varchar(255),
        cst_icms varchar(255),
        data_emissao date,
        numero_nfce int8,
        quant_conv numeric(19, 2),
        unid varchar(255),
        vl_unit_conv numeric(19, 2),
        vl_unit_fcp_icms_st_estoque_conv numeric(19, 2),
        vl_unit_fcp_st_conv_compl numeric(19, 2),
        vl_unit_fcp_st_conv_rest numeric(19, 2),
        vl_unit_icms_na_operacao_conv numeric(19, 2),
        vl_unit_icms_op_conv numeric(19, 2),
        vl_unit_icms_op_estoque_conv numeric(19, 2),
        vl_unit_icms_st_conv_compl numeric(19, 2),
        vl_unit_icms_st_conv_rest numeric(19, 2),
        vl_unit_icms_st_estoque_conv numeric(19, 2),
        primary key (chave, id_empresa, num_item)
    )

    create table public.relacao_xmls_cte_es (
       chave varchar(255) not null,
        cpf_cnpj_emitente varchar(255),
        data_emissao date,
        numero int8,
        situacao varchar(255),
        uf_emitente varchar(255),
        id_empresa int8 not null,
        primary key (chave, id_empresa)
    )

    create table public.relacao_xmls_nfe_es (
       chave varchar(255) not null,
        cpf_cnpj_emitente varchar(255),
        data_emissao date,
        numero int8,
        situacao varchar(255),
        id_empresa int8 not null,
        primary key (chave, id_empresa)
    )

    alter table public.au_cte_nfes 
       add constraint FKjxqqcsg4s0tvejgf0eid0vw3k 
       foreign key (id_cte) 
       references public.au_cte

    alter table public.au_extrato_saldo 
       add constraint FK2cj8yjw8yyqcqo2lajdvfoc41 
       foreign key (id_empresa) 
       references public.au_empresa

    alter table public.au_nfce_item 
       add constraint FKigxk47ptctcdou8qso111v6f7 
       foreign key (id_nfce) 
       references public.au_nfce

    alter table public.au_nfe_item 
       add constraint FKek7p2hq7934x6jvwq0k2teeek 
       foreign key (id_nfe) 
       references public.au_nfe

    alter table public.au_pauta_comb_registros 
       add constraint FKbmopgqbyg0fmofaxegax3mmix 
       foreign key (id_pauta_combustiveis) 
       references public.au_pauta_combustiveis

    alter table public.au_scontabil_registros 
       add constraint FKdb9n13owsqabetfwhns7kpbur 
       foreign key (id_empresa, lote) 
       references public.au_scontabil_lote

    alter table public.au_secf_registros 
       add constraint FKnck6boyy8samdh1tde84y3599 
       foreign key (id_empresa, lote) 
       references public.au_secf_lote

    alter table public.au_sped_contrib_registros 
       add constraint FKllc7u7dyho5at9oq6p3706dyj 
       foreign key (id_empresa, lote) 
       references public.au_sped_contribuicoes_lote

    alter table public.au_sped_fiscal_registros 
       add constraint FKkn1b48s7r9geb3cp5pqybty21 
       foreign key (id_empresa, lote) 
       references public.au_sped_fiscal_lote

    alter table public.cod_cigarro 
       add constraint FKi6sf6mh93llu6v1g99i6t6ll4 
       foreign key (id_empresa) 
       references public.au_empresa

    alter table public.relacao_xmls_cte_es 
       add constraint FKr1li97m24gwfidst70dx6fr7q 
       foreign key (id_empresa) 
       references public.au_empresa

    alter table public.relacao_xmls_nfe_es 
       add constraint FKi93vyr2s6un7c5hi6v4bvgbau 
       foreign key (id_empresa) 
       references public.au_empresa
