package creatorInvoice.dto.invoice.modify.position.delete;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeleteInvoicePositionDto {

    private Long id;
    private String api_token;
    private InvoiceDto invoice;

}
