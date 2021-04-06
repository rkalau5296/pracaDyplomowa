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

    @RequestMapping(method = RequestMethod.GET, value = "/getName/{name}")
    public List<ClientDto> getClientsByName(@PathVariable String name) {

        return clientFacade.fetchClientsByName(name);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getEmail/{email_address}")
    public List<ClientDto> getClientsByEmailAddress(@PathVariable String email_address) {

        return clientFacade.fetchClientsByEmailAddress(email_address);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getShortcut/{short_name}")
    public List<ClientDto> getClientsByShortName(@PathVariable String short_name) {

        return clientFacade.fetchClientsByShortName(short_name);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getTaxNo/{tax_no}")
    public List<ClientDto> getClientsByTaxNo(@PathVariable String tax_no) {

        return clientFacade.fetchClientsByTaxNo(tax_no);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getId/{id}")
    public ClientDto getClient (@PathVariable Long id) {
        return clientFacade.fetchClientById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public ClientDto addCLient (@RequestBody AddClientDto addClientDto) {
        return clientFacade.addClient(addClientDto);
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
