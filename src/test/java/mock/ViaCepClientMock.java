package mock;

import com.dynns.cloudtecnologia.rest.client.ViaCepClient;
import com.dynns.cloudtecnologia.rest.dto.EnderecoDTO;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@ApplicationScoped
@RestClient
public class ViaCepClientMock implements ViaCepClient {

    @Override
    public EnderecoDTO getEnderecoByCep(String cep) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep("teste");
        enderecoDTO.setLocalidade("teste");
        enderecoDTO.setBairro("teste");
        enderecoDTO.setUf("teste");
        enderecoDTO.setGia("teste");
        enderecoDTO.setDdd("teste");
        enderecoDTO.setComplemento("teste");
        enderecoDTO.setDdd("teste");
        enderecoDTO.setSiafi("teste");

        return enderecoDTO;
    }

}
