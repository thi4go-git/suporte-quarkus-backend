package com.dynns.cloudtecnologia.model.enums;

public enum NacionalidadeEnum {
    BRASILEIRO(1),
    ESTRANGEIRO(2);

    private Integer codigo;

    NacionalidadeEnum(Integer codigo) {
        this.codigo = codigo;
    }
}
