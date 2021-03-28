package creatorInvoice.validator;

import creatorInvoice.dto.product.AddProductDto;
import creatorInvoice.dto.product.ProductDto;
import creatorInvoice.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductValidator.class);

    public List<ProductDto> validateProducts(final List<ProductDto> products) {
        LOGGER.info("Start fetching products...");
        LOGGER.info("Products have been fetched. Products list size: " + products.size());
        return products;
    }
    public void validateProductById(final Long id) {
        LOGGER.info("Starting fetching product id = " + id);
        LOGGER.info("Product id = " + id + " has been fetched.");
    }

    public void validateCreatingProduct(final AddProductDto addProductDto) {
        LOGGER.info("Starting creating a new product name = " + addProductDto.getProduct().getName());
        LOGGER.info("Product name = " + addProductDto.getProduct().getName() + " has been created.");
    }

    public void validateDeletingProduct(final Long id) {
        LOGGER.info("Starting deleting product id = " + id);
        LOGGER.info("Product id = " + id + " has been deleted.");
    }
    public void validateUpdateProduct(final AddProductDto addProductDto, Long id) {
        LOGGER.info("Starting updating product id = " + id + " " + addProductDto.getProduct().getName());
        LOGGER.info("Product id = " + id + " has been updated.");
    }

}
