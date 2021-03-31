package creatorInvoice.dto.invoice.modify.position;


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
    private InvoiceDto invoice;

}
