package creatorInvoice.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddInvoiceDto {

    private String api_token;
    private InvoiceDto invoiceDto;

}
