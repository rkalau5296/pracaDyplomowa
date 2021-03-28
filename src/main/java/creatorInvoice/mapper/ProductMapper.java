package creatorInvoice.mapper;

import creatorInvoice.dto.product.ProductDto;
import creatorInvoice.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProductMapper {
    public List<Product> mapToListClients(final List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(p -> new Product(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice_net(),
                        p.getTax(),
                        p.getCreated_at(),
                        p.getUpdated_at(),
                        p.getPrice_gross(),
                        p.getPrice_tax(),
                        p.getForm_name(),
                        p.getForm_description()
                ))
                .collect(toList());
    }

}
