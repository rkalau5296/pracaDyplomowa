package creatorInvoice.dto.invoice.modify.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class InvoicePositionDto {

    private Long id;
    private String name;
    private String description;
    private String price_net;
    private String quantity;
    private String total_price_gross;
    private String total_price_net;
    private Date created_at;
    private Date updated_at;
    private String tax;
    private String price_gross;
    private String price_tax;
    private String total_price_tax;

}
