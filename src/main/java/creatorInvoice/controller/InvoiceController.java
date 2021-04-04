package creatorInvoice.controller;

import creatorInvoice.dto.invoice.modify.buyer_name.UpdateBuyerDetailsInvoiceDto;
import creatorInvoice.dto.invoice.modify.position.UpdatePositionInvoiceDto;
import creatorInvoice.dto.invoice.add.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.invoice.modify.position.addnew.AddNewPosition;
import creatorInvoice.dto.invoice.modify.position.delete.DeleteInvoicePositionDto;
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

    @RequestMapping(method = RequestMethod.GET, value = "/getActualMonth")
    public List<InvoiceDto> getInvoicesActualMonth() {
        return invoiceFacade.fetchInvoicesActualMonth();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public InvoiceDto getInvoice (@PathVariable Long id) {
        return invoiceFacade.fetchInvoiceById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public InvoiceDto createInvoice (@RequestBody AddInvoiceDto addInvoiceDto) {
        return invoiceFacade.createInvoice(addInvoiceDto);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/put/buyer/{id}")
    public void updateBuyerDetailsInvoice (@RequestBody UpdateBuyerDetailsInvoiceDto updateBuyerDetailsInvoiceDto, @PathVariable Long id){
        invoiceFacade.updateBuyerDetailsInvoice(updateBuyerDetailsInvoiceDto, id);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/put/position/{id}")
    public void updatePositionDetailsInvoice (@RequestBody UpdatePositionInvoiceDto updatePositionInvoiceDto, @PathVariable Long id){
        invoiceFacade.updatePositionInvoice(updatePositionInvoiceDto, id);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/put/deletePosition/{id}")
    public void deletePositionDetailsInvoice (@RequestBody DeleteInvoicePositionDto deleteInvoicePositionDto, @PathVariable Long id){
        invoiceFacade.deletePositionInvoice(deleteInvoicePositionDto, id);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/put/addNextPosition/{id}")
    public void addNextPosition (@RequestBody AddNewPosition addNewPosition, @PathVariable Long id){
        invoiceFacade.addNexPositionToInvoice(addNewPosition, id);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteInvoice (@PathVariable Long id){
        invoiceFacade.deleteInvoice(id);
    }


}
