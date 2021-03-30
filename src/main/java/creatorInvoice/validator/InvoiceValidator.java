package creatorInvoice.validator;


import creatorInvoice.dto.invoice.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.invoice.UpdateBuyerDetailsInvoiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceValidator.class);

    public void validateInvoices(final List<InvoiceDto> invoices) {
        LOGGER.info("Starting fetching invoices...");
        LOGGER.info("Invoices have been fetched. Current list size: " + invoices.size());
    }
    public void validatingInvoice(final List<InvoiceDto> invoices) {
        LOGGER.info("Starting fetching invoices...");
        LOGGER.info("Invoices have been fetched. Current list size: " + invoices.size());
    }
    public void validateInvoicesById(final Long id) {
        LOGGER.info("Starting fetching invoice id = " + id);
        LOGGER.info("Invoice id = " + id + " has been fetched.");
    }

    public void validateCreatingInvoice(final AddInvoiceDto addInvoiceDto) {
        LOGGER.info("Starting creating a new invoice = " + addInvoiceDto.getInvoice().getId());
        LOGGER.info("Invoice = " + addInvoiceDto.getInvoice().getClient_id() + " has been created.");
    }

    public void validateDeletingInvoice(final Long id) {
        LOGGER.info("Starting deleting invoice id = " + id);
        LOGGER.info("Invoice id = " + id + " has been deleted.");
    }
    public void validateUpdateInvoice(final UpdateBuyerDetailsInvoiceDto updateBuyerDetailsInvoiceDto, Long id) {
        LOGGER.info("Starting updating invoice id = " + id + " " + updateBuyerDetailsInvoiceDto);
        LOGGER.info("Invoice id = " + id + " has been updated.");
    }

}
