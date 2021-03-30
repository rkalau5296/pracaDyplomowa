package creatorInvoice.dto.invoice;

import creatorInvoice.model.invoice.position.modify.Invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UpdatePositionInvoiceDto {

    private Long id;
    private String api_token;
    private Invoice invoice;

}
