package creatorInvoice.facade;

import creatorInvoice.dto.invoice.add.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.invoice.modify.buyer_name.UpdateBuyerDetailsInvoiceDto;
import creatorInvoice.dto.invoice.modify.position.UpdatePositionInvoiceDto;
import creatorInvoice.dto.invoice.modify.position.addnew.AddNewPosition;
import creatorInvoice.dto.invoice.modify.position.delete.DeleteInvoicePositionDto;
import creatorInvoice.mapper.InvoiceMapper;
import creatorInvoice.model.Invoice;
import creatorInvoice.model.InvoicePosition;
import creatorInvoice.repository.InvoicePositionRepository;
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
    @Autowired
    private InvoicePositionRepository invoicePositionRepository;

    public List<InvoiceDto> fetchInvoices() {
        List<InvoiceDto> filteredInvoices = invoiceService.fetchInvoices();
        List<Invoice> invoices =  invoiceMapper.mapToListInvoices(filteredInvoices);
        invoices.forEach(invoice -> invoiceRepository.save(invoice));
        invoiceValidator.validateInvoices(filteredInvoices);
        return filteredInvoices;
    }
    public List<InvoiceDto> fetchInvoicesActualMonth() {
        List<InvoiceDto> filteredInvoices = invoiceService.fetchInvoicesActualMonth();
        invoiceValidator.validateInvoicesActualMonth(filteredInvoices);
        return filteredInvoices;
    }

    public List<InvoiceDto> fetchParticularClientInvoices(Long clientId) {
        List<InvoiceDto> filteredInvoices = invoiceService.fetchParticularClientInvoices(clientId);
        invoiceValidator.fetchParticularClientInvoices(filteredInvoices);
        return filteredInvoices;
    }
    public InvoiceDto fetchInvoiceById(Long id) {
        invoiceValidator.validateInvoicesById(id);
        InvoiceDto invoiceDto = invoiceService.fetchInvoiceById(id);
        List<InvoicePosition> positions = invoiceDto.getPositions();
        positions.forEach(position ->invoicePositionRepository.save(position));
        return invoiceDto;
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
    public void updateBuyerDetailsInvoice(final UpdateBuyerDetailsInvoiceDto updateBuyerDetailsInvoiceDto, Long id) {
        invoiceValidator.validateUpdateBuyerInvoice(updateBuyerDetailsInvoiceDto, id);
        invoiceService.updateBuyerInvoice(updateBuyerDetailsInvoiceDto, id);
    }
    public void updatePositionInvoice(final UpdatePositionInvoiceDto updatePositionInvoiceDto, Long id) {
        invoiceValidator.validateUpdatePostionInvoice(updatePositionInvoiceDto, id);
        invoiceService.updatePositionInvoice(updatePositionInvoiceDto, id);
    }


    public void deletePositionInvoice(DeleteInvoicePositionDto deleteInvoicePositionDto, Long id) {
        invoiceValidator.validateDeletingPositionInvoice(deleteInvoicePositionDto, id);
        invoiceService.deletePositionInvoice(deleteInvoicePositionDto, id);
    }

    public void addNexPositionToInvoice(AddNewPosition addNewPosition, Long id) {
        invoiceValidator.validateAddPositionInvoice(addNewPosition, id);
        invoiceService.addNextPositionInvoice(addNewPosition, id);
    }



}
