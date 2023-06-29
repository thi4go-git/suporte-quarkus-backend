package com.dynns.cloudtecnologia.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(final String mensagem) {
        super("Entidade: " + mensagem);
    }
}
