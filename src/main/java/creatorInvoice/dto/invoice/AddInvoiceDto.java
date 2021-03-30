package creatorInvoice.dto.invoice;

import creatorInvoice.model.invoice.position.add.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddInvoiceDto {

    private Long id;
    private String api_token;
    private Invoice invoice;

}
