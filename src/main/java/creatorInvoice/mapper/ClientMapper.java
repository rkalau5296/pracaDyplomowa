package creatorInvoice.mapper;

import creatorInvoice.dto.client.ClientDto;
import creatorInvoice.model.Client;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ClientMapper {

    public List<Client> mapToListClients(final List<ClientDto> clientDto) {
        return clientDto.stream()
                .map(p -> new Client(
                        p.getId(),
                        p.getName(),
                        p.getTax_no(),
                        p.getPost_code(),
                        p.getCity(),
                        p.getStreet(),
                        p.getFirst_name(),
                        p.getCountry(),
                        p.getEmail(),
                        p.getPhone(),
                        p.getWww(),
                        p.getFax(),
                        p.getCreated_at(),
                        p.getUpdated_at(),
                        p.getStreet_no(),
                        p.getKind(),
                        p.getBank(),
                        p.getBank_account(),
                        p.getShortcut(),
                        p.getLast_name(),
                        p.getMobile_phone(),
                        p.getRegister_number(),
                        p.getTax_no_check()
                ))
                .collect(toList());
    }

}
