package creatorInvoice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDto {

    private int id;
    
    private String name;
    
    private String description;
    
    private String price_net;
    
    private String tax;
    
    private Date created_at;
    
    private Date updated_at;
    
    private String price_gross;
    
    private String price_tax;
    
    private String form_name;
    
    private String form_description;
}
