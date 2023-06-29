package com.dynns.cloudtecnologia.model.entity;


import com.dynns.cloudtecnologia.model.enums.NacionalidadeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Data
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 11, unique = true, updatable = false)
    private String cpf;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "titulo_eleitoral", nullable = false, length = 12, unique = true)
    private String tituloEleitoral;

    @Column(nullable = false)
    private NacionalidadeEnum nacionalidade;

    @Column
    private boolean ativo;


}
