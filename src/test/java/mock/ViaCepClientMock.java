package mock;

import com.dynns.cloudtecnologia.rest.client.ViaCepClient;
import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@ApplicationScoped
@RestClient
public class ViaCepClientMock implements ViaCepClient {

    @Override
    public EnderecoDTO getEnderecoByCep(String cep) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep("teste1");
        enderecoDTO.setLocalidade("teste2");
        enderecoDTO.setBairro("teste3");
        enderecoDTO.setUf("teste4");
        enderecoDTO.setGia("teste5");
        enderecoDTO.setDdd("teste6");
        enderecoDTO.setComplemento("teste7");
        enderecoDTO.setDdd("teste8");
        enderecoDTO.setSiafi("teste9");

        return enderecoDTO;
    }

}
