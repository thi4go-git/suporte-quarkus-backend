package com.dynns.cloudtecnologia.model.repository;

import com.dynns.cloudtecnologia.exception.EntidadeNaoEncontradaException;
import com.dynns.cloudtecnologia.model.entity.Pessoa;
import com.dynns.cloudtecnologia.rest.dto.PessoaReflectionDTO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {

    public Pessoa findByCpf(String cpf) {
        return find("cpf =:cpf",
                Parameters.with("cpf", cpf)).
                firstResultOptional().orElse(new Pessoa());
    }

    public Pessoa findByCpfOrThrow(String cpf) {
        return find("cpf =:cpf",
                Parameters.with("cpf", cpf)).
                firstResultOptional()
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("'Pessoa', cpf '" + cpf + "' Não localizada! "));
    }

    public Pessoa findByIdOrThrow(Long id) {
        return find("id =:id",
                Parameters.with("id", id)).
                firstResultOptional()
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("'Pessoa', id '" + id + "' Não localizada! "));
    }

    public Pessoa findByTituloEleitoral(String tituloParam) {
        return find("tituloEleitoral = ?1", tituloParam)
                .firstResultOptional().orElse(new Pessoa());
    }

    public List<PessoaReflectionDTO> getPessoasReflection() {
        String query = "SELECT id,nome,cpf,telefone from Pessoa order by nome asc";
        PanacheQuery<PessoaReflectionDTO> panache = find
                (query).project(PessoaReflectionDTO.class);

        return panache.list();
    }

}
