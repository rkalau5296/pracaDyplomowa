package creatorInvoice.facade;

import creatorInvoice.dto.client.AddClientDto;
import creatorInvoice.dto.client.ClientDto;
import creatorInvoice.mapper.ClientMapper;
import creatorInvoice.model.Client;
import creatorInvoice.repository.ClientRepository;
import creatorInvoice.service.ClientService;
import creatorInvoice.url.Url;
import creatorInvoice.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientFacade {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private ClientRepository clientRepository;


    public List<ClientDto> fetchClients() {
        List<ClientDto> clientDtoList = clientService.fetchClients();
        clientValidator.validateClients(clientDtoList);
        List<Client> clients = clientMapper.mapToListClients(clientDtoList);
        clients.forEach(client->clientRepository.save(client));
        return clientDtoList;
    }

    public ClientDto fetchClientById(Long id) {
        clientValidator.validateClientById(id);
        return clientService.fetchClientById(id);
    }

    public ClientDto addClient(final AddClientDto addClientDto) {
        clientValidator.validateCreatingClient(addClientDto);
        return clientService.createCustomer(addClientDto);
    }
    public void updateClient(final AddClientDto addClientDto, Long id) {
        clientValidator.validateUpdateClient(addClientDto, id);
        clientService.updateCustomer(addClientDto, id);
    }
    public void deleteClient(Long id) {
        clientValidator.validateDeletingClient(id);
        clientService.deleteCustomer(id);
    }

}
