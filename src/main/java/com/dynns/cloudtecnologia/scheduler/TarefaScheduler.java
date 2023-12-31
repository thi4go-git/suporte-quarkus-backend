package com.dynns.cloudtecnologia.scheduler;

import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class TarefaScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(TarefaScheduler.class);


    @Scheduled(every = "60s", identity ="task-job")
    void executarTarefa(){
        LOG.info("Tarefa executada: "+ LocalDateTime.now());
    }

}
