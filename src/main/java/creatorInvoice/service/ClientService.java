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

    private static final String SUBJECT = "New customer to fakturownia.pl";
    private static final String SUBJECT_DELETE = "Delete customer from fakturownia.pl";
    private static final String SUBJECT_UPDATE = "Update customer in fakturownia.pl";

    public List<ClientDto> fetchClients() {

        return url.getCustomers();
    }

    public ClientDto fetchClientById(Long id) {

        return url.getCustomerById(id);
    }

    public ClientDto createCustomer(final AddClientDto addClientDto) {
        ClientDto newClient = url.postCustomer(addClientDto);
               ofNullable(newClient).ifPresent(clientDto ->  emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New client: "+ addClientDto.getClient().getName() + " has been created, and sent to fakturownia.pl.")));
               return newClient;

    }
    public void updateCustomer(final AddClientDto addClientDto, Long id) {
        url.updateCustomer(addClientDto, id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The client id = " + id + " has been updated, and sent to fakturownia.pl. New customer name is " + addClientDto.getClient().getName()));
    }
    public void deleteCustomer(Long id) {
        url.deleteCustomerById(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The customer id = " + id + " has been deleted from fakturownia.pl."));
    }
}
