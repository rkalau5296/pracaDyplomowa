package creatorInvoice.validator;


import creatorInvoice.dto.invoice.add.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.invoice.modify.buyer_name.UpdateBuyerDetailsInvoiceDto;
import creatorInvoice.dto.invoice.modify.position.UpdatePositionInvoiceDto;
import creatorInvoice.dto.invoice.modify.position.addnew.AddNewPosition;
import creatorInvoice.dto.invoice.modify.position.delete.DeleteInvoicePositionDto;
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
    public void validateInvoicesActualMonth(List<InvoiceDto> invoiceDtos) {
        LOGGER.info("Starting fetching invoices...");
        LOGGER.info("Invoices have been fetched. Current list size: " + invoiceDtos.size());
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
    public void validateUpdateBuyerInvoice(final UpdateBuyerDetailsInvoiceDto updateBuyerDetailsInvoiceDto, Long id) {
        LOGGER.info("Starting updating invoice id = " + id + " " + updateBuyerDetailsInvoiceDto.getInvoice());
        LOGGER.info("Invoice id = " + id + " has been updated.");
    }

    public void validateUpdatePostionInvoice(final UpdatePositionInvoiceDto updatePositionInvoiceDto, Long id) {
        LOGGER.info("Starting updating invoice id = " + id + " " + updatePositionInvoiceDto.getInvoice());
        LOGGER.info("Invoice id = " + id + " has been updated.");
    }

    public void validateDeletingPositionInvoice(DeleteInvoicePositionDto deleteInvoicePositionDto, Long id) {
        LOGGER.info("Starting updating invoice id = " + id + " " + deleteInvoicePositionDto.getInvoice());
        LOGGER.info("Invoice id = " + id + " has been updated.");
    }

    public void validateAddPositionInvoice(AddNewPosition addNewPosition, Long id) {
        LOGGER.info("Starting updating invoice id = " + id + " " + addNewPosition.getInvoice());
        LOGGER.info("Invoice id = " + id + " has been updated.");
    }


}
