package creatorInvoice.validator;

import creatorInvoice.dto.client.AddClientDto;
import creatorInvoice.dto.client.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientValidator.class);

    public List<ClientDto> validateClients(final List<ClientDto> clients) {
        LOGGER.info("Start fetching clients...");
        LOGGER.info("Clients have been fetched. Current list size: " + clients.size());
        return clients;
    }
    public void validateClientById(final Long id) {
        LOGGER.info("Starting fetching Client id: " + id);
        LOGGER.info("Client id: " + id + " has been fetched.");
    }

    public void validateCreatingClient(final AddClientDto addClientDto) {
        LOGGER.info("Starting creating a new Client: " + addClientDto.getClient().getShortcut());
        LOGGER.info("Client: " + addClientDto.getClient().getShortcut() + " has been created.");
    }

    public void validateDeletingClient(final Long id) {
        LOGGER.info("Starting deleting Client id: " + id);
        LOGGER.info("Client id: " + id + " has been deleted.");
    }
    public void validateUpdateClient(final AddClientDto addClientDto, Long id) {
        LOGGER.info("Starting updating Client id: " + id + " and name:" + addClientDto.getClient().getShortcut());
        LOGGER.info("Client id = " + id + " has been updated.");
    }

    public void validateClientsByName(String name) {
        LOGGER.info("Starting fetching Client name: " + name);
        LOGGER.info("Client name: " + name + " has been fetched.");
    }

    public void validateClientByEmailAddress(String email_address) {
        LOGGER.info("Starting fetching Client email_address: " + email_address);
        LOGGER.info("Client email_address: " + email_address + " has been fetched.");
    }

    public void validateClientByShortName(String short_name) {
        LOGGER.info("Starting fetching Client short_name: " + short_name);
        LOGGER.info("Client short_name: " + short_name + " has been fetched.");
    }

    public void validateClientsByTaxNo(String tax_no) {
        LOGGER.info("Starting fetching Client tax_no: " + tax_no);
        LOGGER.info("Client tax_no: " + tax_no + " has been fetched.");
    }
}
