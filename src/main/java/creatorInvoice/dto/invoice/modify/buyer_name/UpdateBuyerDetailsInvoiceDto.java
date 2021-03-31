package creatorInvoice.dto.invoice.modify.buyer_name;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UpdateBuyerDetailsInvoiceDto {

    private Long id;
    private String api_token;
    private InvoiceDto invoice;
}
