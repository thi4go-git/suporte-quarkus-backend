package com.dynns.cloudtecnologia.infra.message.kafka;

import com.dynns.cloudtecnologia.rest.dto.PessoaDTONew;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class PublisherKafka {


    private static final Logger LOG = LoggerFactory.getLogger(PublisherKafka.class);

    @Channel("pessoas")
    Emitter<PessoaDTONew> pessoaDTONewEmitter;


    public void enviarPessoaKafka(PessoaDTONew pessoaDTONew) {
        pessoaDTONewEmitter.send(pessoaDTONew).toCompletableFuture().join();
        LOG.info("Enviado para o KAFKA: " + pessoaDTONew.toString());
    }
}
