package creatorInvoice.controller;

import creatorInvoice.dto.invoice.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.invoice.UpdateBuyerNameInvoiceDto;
import creatorInvoice.facade.InvoiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/invoices")
@CrossOrigin("*")
public class InvoiceController {

    @Autowired
    private InvoiceFacade invoiceFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<InvoiceDto> getInvoices() {
        return invoiceFacade.fetchInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public InvoiceDto getInvoice (@PathVariable Long id) {
        return invoiceFacade.fetchInvoiceById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public InvoiceDto createInvoice (@RequestBody AddInvoiceDto addInvoiceDto) {
        return invoiceFacade.createInvoice(addInvoiceDto);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/put/{id}")
    public void updateInvoice (@RequestBody UpdateBuyerNameInvoiceDto updateBuyerNameInvoiceDto, @PathVariable Long id){
        invoiceFacade.updateInvoice(updateBuyerNameInvoiceDto, id);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteInvoice (@PathVariable Long id){
        invoiceFacade.deleteInvoice(id);
    }


}
