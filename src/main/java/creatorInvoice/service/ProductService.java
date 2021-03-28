package creatorInvoice.service;

import creatorInvoice.config.AdminConfig;
import creatorInvoice.dto.product.AddProductDto;
import creatorInvoice.dto.product.ProductDto;
import creatorInvoice.mail.Mail;
import creatorInvoice.mail.SimpleEmailService;
import creatorInvoice.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class ProductService {

    @Autowired
    private Url url;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New product to fakturownia.pl";
    private static final String SUBJECT_DELETE = "Delete product from fakturownia.pl";
    private static final String SUBJECT_UPDATE = "Update product in fakturownia.pl";

    public List<ProductDto> fetchProducts() {
        return url.getProducts();
    }
    public ProductDto fetchProductById(Long id) {
        return url.getProductById(id);
    }
    public ProductDto createProduct(final AddProductDto addProductDto) {
        ProductDto newProduct = url.postProduct(addProductDto);
        ofNullable(newProduct).ifPresent(product->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New product: "+ product.getName() + " has been created, and sent to fakturownia.pl.")));
        return newProduct;
    }
    public void updateProduct(final AddProductDto addProductDto, Long productId) {
        url.updateProduct(addProductDto, productId);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The product id = " + productId + " has been updated, and sent to fakturownia.pl. New product name is " + addProductDto.getProduct().getName()));
    }
    public void deleteById(Long id) {
        url.deleteProductById(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The product id = " + id + " has been deleted from fakturownia.pl."));
    }

}
