package com.dynns.cloudtecnologia.rest.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@RegisterForReflection
public class PessoaReflectionDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
}
