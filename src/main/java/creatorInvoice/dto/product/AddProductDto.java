package creatorInvoice.dto.product;

import creatorInvoice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddProductDto {


    private String api_token;
    private ProductDto product;

}
