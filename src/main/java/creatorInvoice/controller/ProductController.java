package creatorInvoice.controller;

import creatorInvoice.dto.product.AddProductDto;
import creatorInvoice.dto.product.ProductDto;
import creatorInvoice.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<ProductDto> getProducts() {
        return productFacade.fetchProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public ProductDto getProduct (@PathVariable Long id) {
        return productFacade.fetchProductById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public ProductDto createProduct (@RequestBody AddProductDto addProductDto) {
        return productFacade.createProduct(addProductDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteProduct (@PathVariable Long id){
        productFacade.deletedById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/put/{id}")
    public void updateProduct (@RequestBody AddProductDto addProductDto, @PathVariable Long id){
        productFacade.updateProduct(addProductDto, id);
    }
}
