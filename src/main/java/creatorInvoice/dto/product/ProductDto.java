package creatorInvoice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
