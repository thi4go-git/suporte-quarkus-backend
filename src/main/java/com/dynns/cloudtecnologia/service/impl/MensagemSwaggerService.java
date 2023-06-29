package com.dynns.cloudtecnologia.service.impl;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MensagemSwaggerService {

    private MensagemSwaggerService (){

    }

    public static final String MSG_DTO_CREATE = "DTO do objeto a ser criado";
    public static final String MSG_DTO_UPDATE = "DTO do objeto a ser criado";
    public static final String MSG_SUCESSO = "Executado com sucesso!";
    public static final String STATUS_NOT_FOUND = "404";
    public static final String STATUS_OK = "200";
    public static final String STATUS_CREATED = "201";
    public static final String MSG_SEM_REGISTROS = "Sem Registro(s)!";
    public static final String STATUS_NO_CONTENT = "204";
    public static final String MSG_DELETADO = "Registro deletado com sucesso!";
    public static final String MSG_ATUALIZADO = "Registro atualizado com sucesso!";
}
