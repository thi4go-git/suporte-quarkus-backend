package com.dynns.cloudtecnologia.infra.message.kafka;

import com.dynns.cloudtecnologia.rest.dto.PessoaDTONew;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class PublisherKafka {


    private static final Logger LOG = LoggerFactory.getLogger(PublisherKafka.class);

    @Inject
    @Channel("pessoas")
    Emitter<PessoaDTONew> pessoaDTONewEmitter;


    public void enviarPessoaKafka(PessoaDTONew pessoaDTONew) {
        pessoaDTONewEmitter.send(pessoaDTONew);
        LOG.info("Enviado para o KAFKA: " + pessoaDTONew.toString());
    }
}
