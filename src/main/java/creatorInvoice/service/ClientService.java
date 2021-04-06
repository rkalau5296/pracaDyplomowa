package creatorInvoice.service;

import creatorInvoice.config.AdminConfig;
import creatorInvoice.dto.client.AddClientDto;
import creatorInvoice.dto.client.ClientDto;
import creatorInvoice.mail.Mail;
import creatorInvoice.mail.SimpleEmailService;
import creatorInvoice.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class ClientService {

    @Autowired
    private Url url;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New Client to fakturownia.pl";
    private static final String SUBJECT_DELETE = "Delete Client from fakturownia.pl";
    private static final String SUBJECT_UPDATE = "Update Client in fakturownia.pl";

    public List<ClientDto> fetchClients() {

        return url.getClients();
    }

    public ClientDto fetchClientById(Long id) {

        return url.getClientById(id);
    }

    public ClientDto createClient(final AddClientDto addClientDto) {
        ClientDto newClient = url.postClient(addClientDto);
               ofNullable(newClient).ifPresent(clientDto ->  emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New product: "+ addClientDto.getClient().getName() + " has been created, and sent to fakturownia.pl.")));
               return newClient;

    }
    public void updateClient(final AddClientDto addClientDto, Long id) {
        url.updateClient(addClientDto, id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The product id = " + id + " has been updated, and sent to fakturownia.pl. New customer name is " + addClientDto.getClient().getName()));
    }
    public void deleteClient(Long id) {
        url.deleteClient(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The product id = " + id + " has been deleted from fakturownia.pl."));
    }

    public List<ClientDto> fetchClientsByName(String name) {
        return url.getClientsByName(name);
    }

    public List<ClientDto> fetchClientByEmailAddress(String email_address) {
        return url.getClientsByEmailAddress(email_address);
    }

    public List<ClientDto> fetchClientsByShortName(String short_name) {
        return url.getClientsByShortName(short_name);
    }

    public List<ClientDto> fetchClientsByTaxNo(String tax_no) {
        return url.getClientsByTaxNo(tax_no);
    }
}
