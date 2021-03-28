package creatorInvoice.facade;

import creatorInvoice.dto.product.AddProductDto;
import creatorInvoice.dto.product.ProductDto;
import creatorInvoice.mapper.ProductMapper;
import creatorInvoice.model.Product;
import creatorInvoice.repository.ProductRepository;
import creatorInvoice.service.ProductService;
import creatorInvoice.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductValidator productValidator;
    @Autowired
    private ProductRepository productRepository;


    public List<ProductDto> fetchProducts() {
        List<ProductDto> fetchedProducts = productService.fetchProducts();
        productValidator.validateProducts(fetchedProducts);
        List<Product> products =  productMapper.mapToListClients(fetchedProducts);
        products.forEach(product -> productRepository.save(product));
        return fetchedProducts;
    }

    public ProductDto fetchProductById(Long id) {
        productValidator.validateProductById(id);
        return productService.fetchProductById(id);
    }

    public ProductDto createProduct(final AddProductDto addProductDto) {
        productValidator.validateCreatingProduct(addProductDto);
        return productService.createProduct(addProductDto);
    }

    public void deletedById(Long id) {
        productValidator.validateDeletingProduct(id);
        productService.deleteById(id);
    }

    public void updateProduct(final AddProductDto addProductDto, Long id) {
        productValidator.validateUpdateProduct(addProductDto,id);
        productService.updateProduct(addProductDto, id);
    }
}
