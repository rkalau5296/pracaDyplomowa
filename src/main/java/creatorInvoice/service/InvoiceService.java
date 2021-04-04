package creatorInvoice.service;

import creatorInvoice.config.AdminConfig;
import creatorInvoice.dto.invoice.add.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.invoice.modify.buyer_name.UpdateBuyerDetailsInvoiceDto;
import creatorInvoice.dto.invoice.modify.position.UpdatePositionInvoiceDto;
import creatorInvoice.dto.invoice.modify.position.addnew.AddNewPosition;
import creatorInvoice.dto.invoice.modify.position.delete.DeleteInvoicePositionDto;
import creatorInvoice.mail.Mail;
import creatorInvoice.mail.SimpleEmailService;
import creatorInvoice.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class InvoiceService {

    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private Url url;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New Invoice to fakturownia.pl ";
    private static final String SUBJECT_DELETE = "Delete Invoice from fakturownia.pl";
    private static final String SUBJECT_UPDATE = "Update Invoice in fakturownia.pl";

    public List<InvoiceDto> fetchInvoices() {
        return url.getInvoices();
    }

    public List<InvoiceDto> fetchInvoicesActualMonth() {
        return url.getInvoicesActualMonth();
    }
    public InvoiceDto fetchInvoiceById(Long id) {
        return url.getInvoicesById(id);
    }

    public InvoiceDto createInvoice(final AddInvoiceDto addInvoiceDto) {
        InvoiceDto newInvoice = url.postInvoice(addInvoiceDto);
                ofNullable(newInvoice).ifPresent(invoiceDto->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
            "New invoice to: "+ addInvoiceDto.getInvoice().getId() + " has been created, and sent to fakturownia.pl.")));
        return newInvoice;
    }

    public void updateBuyerInvoice(final UpdateBuyerDetailsInvoiceDto updateBuyerDetailsInvoiceDto, Long id){
        url.updateBuyerInvoice(updateBuyerDetailsInvoiceDto, id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The invoice id = " + id + " has been updated, and sent to fakturownia.pl."));

    }

    public void updatePositionInvoice(final UpdatePositionInvoiceDto updatePositionInvoiceDto, Long id){
        url.updatePositionInvoice(updatePositionInvoiceDto, id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The invoice id = " + id + " has been updated, and sent to fakturownia.pl."));

    }
    public void deleteInvoice(Long id) {
        url.deleteInvoice(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The invoice id = " + id + " has been deleted from fakturownia.pl."));
    }


    public void deletePositionInvoice(final DeleteInvoicePositionDto deleteInvoicePositionDto, Long id) {
        url.deletePositionInvoice(deleteInvoicePositionDto, id);
    }

    public void addNextPositionInvoice(AddNewPosition addNewPosition, Long id) {
        url.addNextPositionToInvoice(addNewPosition,id);
    }


}
