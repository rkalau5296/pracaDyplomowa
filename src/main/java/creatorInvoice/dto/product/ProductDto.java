package creatorInvoice.dto.product;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
