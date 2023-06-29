package com.dynns.cloudtecnologia.rest.dto;

import com.dynns.cloudtecnologia.model.enums.NacionalidadeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaDTOView {
    private Long id;
    private String nome;
    private String cpf;
    private String cep;
    private String telefone;
    private String email;
    private LocalDate dataCadastro;
    private String tituloEleitoral;
    private NacionalidadeEnum nacionalidade;
    private boolean ativo;
}
