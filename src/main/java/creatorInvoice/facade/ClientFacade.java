package creatorInvoice.facade;

import creatorInvoice.dto.client.AddClientDto;
import creatorInvoice.dto.client.ClientDto;
import creatorInvoice.mapper.ClientMapper;
import creatorInvoice.model.Client;
import creatorInvoice.repository.ClientRepository;
import creatorInvoice.service.ClientService;
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
        clients.forEach(client -> clientRepository.save(client));
        return clientDtoList;
    }

    public ClientDto fetchClientById(Long id) {
        clientValidator.validateClientById(id);
        return clientService.fetchClientById(id);
    }

    public List<ClientDto> fetchClientsByName(String name) {
        clientValidator.validateClientsByName(name);
        return clientService.fetchClientsByName(name);
    }
    public List<ClientDto> fetchClientsByEmailAddress(String email_address) {
        clientValidator.validateClientByEmailAddress(email_address);
        return clientService.fetchClientByEmailAddress(email_address);
    }
    public List<ClientDto> fetchClientsByShortName(String short_name) {
        clientValidator.validateClientByShortName(short_name);
        return clientService.fetchClientsByShortName(short_name);
    }
    public List<ClientDto> fetchClientsByTaxNo(String tax_no) {
        clientValidator.validateClientsByTaxNo(tax_no);
        return clientService.fetchClientsByTaxNo(tax_no);
    }
    public ClientDto addClient(final AddClientDto addClientDto) {
        clientValidator.validateCreatingClient(addClientDto);
        return clientService.createClient(addClientDto);
    }

    public void updateClient(final AddClientDto addClientDto, Long id) {
        clientValidator.validateUpdateClient(addClientDto, id);
        clientService.updateClient(addClientDto, id);
    }

    public void deleteClient(Long id) {
        clientValidator.validateDeletingClient(id);
        clientService.deleteClient(id);
    }



}
