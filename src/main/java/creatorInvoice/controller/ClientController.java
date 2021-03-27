package creatorInvoice.controller;

import creatorInvoice.dto.client.AddClientDto;
import creatorInvoice.dto.client.ClientDto;
import creatorInvoice.facade.ClientFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientFacade clientFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<ClientDto> getClients() {
        return clientFacade.fetchClients();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getId/{id}")
    public ClientDto getClient (@PathVariable Long id) {
        return clientFacade.fetchClientById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public void addCLient (@RequestBody AddClientDto addClientDto) {
        clientFacade.addClient(addClientDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/put/{id}")
    public void updateClient (@RequestBody AddClientDto addClientDto, @PathVariable Long id) {
        clientFacade.updateClient(addClientDto, id);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteClient (@PathVariable Long id) {
        clientFacade.deleteClient(id);
    }


}
