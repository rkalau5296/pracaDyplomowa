package creatorInvoice.dto.invoice;

import creatorInvoice.model.invoice.buyer_name.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateBuyerDetailsInvoiceDto {

    private Long id;
    private String api_token;
    private Invoice invoice;
}
