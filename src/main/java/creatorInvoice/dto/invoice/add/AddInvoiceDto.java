package creatorInvoice.dto.invoice.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddInvoiceDto {

    private Long id;
    private String api_token;
    private InvoiceDto invoice;

}
