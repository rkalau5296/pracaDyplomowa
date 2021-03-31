package creatorInvoice.mapper;

import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.model.Invoice;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Component
public class InvoiceMapper {

    public List<Invoice> mapToListInvoices(final List<InvoiceDto> invoiceDtos) {
        return
                invoiceDtos.stream()
                .map(p -> new Invoice(
                        p.getId(),
                        p.getNumber(),
                        p.getPlace(),
                        p.getSell_date(),
                        p.getPayment_type(),
                        p.getPrice_net(),
                        p.getPrice_gross(),
                        p.getCurrency(),
                        p.getStatus(),
                        p.getDescription(),
                        p.getSeller_name(),
                        p.getSeller_tax_no(),
                        p.getSeller_street(),
                        p.getSeller_post_code(),
                        p.getSeller_city(),
                        p.getSeller_country(),
                        p.getSeller_email(),
                        p.getSeller_phone(),
                        p.getSeller_fax(),
                        p.getSeller_www(),
                        p.getSeller_person(),
                        p.getSeller_bank(),
                        p.getSeller_bank_account(),
                        p.getBuyer_name(),
                        p.getBuyer_tax_no(),
                        p.getBuyer_post_code(),
                        p.getBuyer_city(),
                        p.getBuyer_street(),
                        p.getBuyer_first_name(),
                        p.getBuyer_country(),
                        p.getCreated_at(),
                        p.getUpdated_at(),
                        p.getClient_id(),
                        p.getPayment_to(),
                        p.getProduct_cache(),
                        p.getProduct_id(),
                        p.getPositions()
                ))
                .collect(toList());
    }
}
