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
        LOGGER.info("Starting fetching Client id = " + id);
        LOGGER.info("Client id = " + id + " has been fetched.");
    }

    public void validateCreatingClient(final AddClientDto addClientDto) {
        LOGGER.info("Starting creating a new Client = " + addClientDto);
        LOGGER.info("Client = " + addClientDto + " has been created.");
    }

    public void validateDeletingClient(final Long id) {
        LOGGER.info("Starting deleting Client id = " + id);
        LOGGER.info("Client id = " + id + " has been deleted.");
    }
    public void validateUpdateClient(final AddClientDto addClientDto, Long id) {
        LOGGER.info("Starting updating Client id = " + id + " " + addClientDto);
        LOGGER.info("Client id = " + id + " has been updated.");
    }

}
