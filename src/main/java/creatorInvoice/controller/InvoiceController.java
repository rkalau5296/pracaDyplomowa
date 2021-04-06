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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@RestController
@RequestMapping("/invoices")
@CrossOrigin("*")
public class InvoiceController {

    @Autowired
    private InvoiceFacade invoiceFacade;

//    @GetMapping("/downloadFile")
//    public void downloadFile(){
//        String dirName = "C:\\downloaded";
//        try {
//            saveFileFromUrlWithJavaIO(
//                    dirName + "\\java_tutorial.pdf", "https://konto-testowe-rk-kodilla.fakturownia.pl/invoices/60116681.pdf?api_token=YpYI69C1wALAg29JS67y");
//            System.out.println("finished");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //GET
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<InvoiceDto> getInvoices() {
        return invoiceFacade.fetchInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getInRange/{dateFrom}/{dateTo}")
    public List<InvoiceDto> getInvoiceInRange(@PathVariable String dateFrom, @PathVariable String dateTo){
        return invoiceFacade.fetchInvoiceInRange(dateFrom, dateTo);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getActualMonth")
    public List<InvoiceDto> getInvoicesActualMonth() {
        return invoiceFacade.fetchInvoicesActualMonth();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getParticularClientInvoices/{clientId}")
    public List<InvoiceDto> getParticularClientInvoices(@PathVariable Long clientId) {
        return invoiceFacade.fetchParticularClientInvoices(clientId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public InvoiceDto getInvoice (@PathVariable Long id) {
        return invoiceFacade.fetchInvoiceById(id);
    }

    //POST

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public InvoiceDto createInvoice (@RequestBody AddInvoiceDto addInvoiceDto) {
        return invoiceFacade.createInvoice(addInvoiceDto);
    }

    //PUT
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

    //DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteInvoice (@PathVariable Long id){
        invoiceFacade.deleteInvoice(id);
    }

    // Using Java IO
//    public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
//            throws IOException {
//        BufferedInputStream in = null;
//        FileOutputStream fout = null;
//        try {
//            in = new BufferedInputStream(new URL(fileUrl).openStream());
//            fout = new FileOutputStream(fileName);
//            byte data[] = new byte[1024];
//            int count;
//                while ((count = in.read(data, 0, 1024)) != -1) {
//                    fout.write(data, 0, count);
//                }
//        } finally {
//            if ( in != null)
//                in .close();
//            if (fout != null)
//                fout.close();
//        }
//    }

}
