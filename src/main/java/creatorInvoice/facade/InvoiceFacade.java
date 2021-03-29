package creatorInvoice.facade;

import creatorInvoice.dto.invoice.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.invoice.UpdateBuyerNameInvoiceDto;
import creatorInvoice.mapper.InvoiceMapper;
import creatorInvoice.model.Invoice;
import creatorInvoice.repository.InvoiceRepository;
import creatorInvoice.service.InvoiceService;
import creatorInvoice.validator.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InvoiceFacade {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceMapper invoiceMapper;


    public List<InvoiceDto> fetchInvoices() {
        List<InvoiceDto> filteredInvoices = invoiceService.fetchInvoices();
        List<Invoice> invoices =  invoiceMapper.mapToListInvoices(filteredInvoices);
        invoices.forEach(invoice -> invoiceRepository.save(invoice));
        invoiceValidator.validateInvoices(filteredInvoices);
        return filteredInvoices;
    }
    public InvoiceDto fetchInvoiceById(Long id) {
        invoiceValidator.validateInvoicesById(id);
        return invoiceService.fetchInvoiceById(id);
    }
    public InvoiceDto createInvoice(final AddInvoiceDto addInvoiceDto) {
        InvoiceDto fetchedInvoice = invoiceService.createInvoice(addInvoiceDto);
        invoiceValidator.validateCreatingInvoice(addInvoiceDto);
        return fetchedInvoice;
    }
    public void deleteInvoice(Long id) {
        invoiceValidator.validateDeletingInvoice(id);
        invoiceService.deleteInvoice(id);
    }
    public void updateInvoice(final UpdateBuyerNameInvoiceDto updateBuyerNameInvoiceDto, Long id) {
        invoiceValidator.validateUpdateInvoice(updateBuyerNameInvoiceDto, id);
        invoiceService.updateInvoice(updateBuyerNameInvoiceDto, id);
    }


}
