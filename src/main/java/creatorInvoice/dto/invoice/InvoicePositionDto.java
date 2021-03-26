package creatorInvoice.dto.invoice;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvoicePositionDto {

    private int id;
    
    private int invoice_id;
    
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
    
    private int product_id;
}
