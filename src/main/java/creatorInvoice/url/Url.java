package creatorInvoice.url;

import creatorInvoice.config.InvoiceConfig;
import creatorInvoice.config.RateConfig;
import creatorInvoice.dto.client.AddClientDto;
import creatorInvoice.dto.client.ClientDto;
import creatorInvoice.dto.invoice.AddInvoiceDto;
import creatorInvoice.dto.invoice.InvoiceDto;
import creatorInvoice.dto.product.AddProductDto;
import creatorInvoice.dto.product.ProductDto;
import creatorInvoice.dto.rate.RateCurrencyDto;
import creatorInvoice.dto.rate.RateTableDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class Url {
    private static final Logger LOGGER = LoggerFactory.getLogger(Url.class);

    @Autowired
    private InvoiceConfig invoiceConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RateConfig rateConfig;

    //Invoice
    public List<InvoiceDto> getInvoices(){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices.json?&" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            InvoiceDto[] invoiceResponse = restTemplate.getForObject(uri, InvoiceDto[].class);
            return Arrays.asList(Optional.ofNullable(invoiceResponse).orElse(new InvoiceDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }
    public InvoiceDto getInvoicesById(Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices/" + id +".json?&" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            return restTemplate.getForObject(uri, InvoiceDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new InvoiceDto();
        }
    }
    public void postInvoice(final AddInvoiceDto addInvoiceDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices.json")
                .build().encode().toUri();
        try{
            restTemplate.postForObject(uri, addInvoiceDto, AddInvoiceDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void updateInvoice(final AddInvoiceDto invoiceDto, Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices/" + id + ".json")
                .build().encode().toUri();
        try{
            restTemplate.put(uri, invoiceDto);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void deleteInvoice(final Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices/" + id + "json?api_token=" + invoiceConfig.getToken())
                .build().encode().toUri();
        try{
            restTemplate.delete(uri);

        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invoice not found" );
        }
    }

    //Product
    public List<ProductDto> getProducts(){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products.json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            ProductDto[] clientResponse = restTemplate.getForObject(uri, ProductDto[].class);
            return Arrays.asList(Optional.ofNullable(clientResponse).orElse(new ProductDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }
    public ProductDto getProductById(Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products/" + id + ".json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            return restTemplate.getForObject(uri, ProductDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ProductDto();
        }
    }
    public ProductDto postProduct(final AddProductDto addProductDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products.json")
                .build().encode().toUri();
        try{
            return restTemplate.postForObject(uri, addProductDto, ProductDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ProductDto();
        }

    }
    public void updateProduct(final AddProductDto addProductDto, Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products/" + id + ".json")
                .build().encode().toUri();
        try{
            restTemplate.put(uri, addProductDto);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void deleteProductById(final Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products/" + id + "json?api_token=" + invoiceConfig.getToken())
                .build().encode().toUri();
        try{
            restTemplate.delete(uri);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found" );
        }
    }

    //Client
    public List<ClientDto> getCustomers(){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients.json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            ClientDto[] clientResponse = restTemplate.getForObject(uri, ClientDto[].class);
            return Arrays.asList(Optional.ofNullable(clientResponse).orElse(new ClientDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }
    public ClientDto getCustomerById(Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients/" + id + ".json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            return restTemplate.getForObject(uri, ClientDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ClientDto();
        }
    }
    public ClientDto postCustomer(final AddClientDto addClientDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients.json")
                .build().encode().toUri();
        try{
             return restTemplate.postForObject(uri, addClientDto, ClientDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ClientDto();
        }
    }
    public void updateCustomer(final AddClientDto addClientDto, Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients/" + id + ".json")
                .build().encode().toUri();
        try{
            restTemplate.put(uri, addClientDto);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void deleteCustomerById(final Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients/" + id + "json?api_token=" + invoiceConfig.getToken())
                .build().encode().toUri();
        try{
            restTemplate.delete(uri);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found" );
        }
    }

    //Rates
    public List<RateTableDto> getRates(String table){
        URI uri = UriComponentsBuilder.fromHttpUrl(rateConfig.getRateEndPoint() + "exchangerates/tables/" + table)
                .build().encode().toUri();
        try{
            RateTableDto[] rateResponse = restTemplate.getForObject(uri, RateTableDto[].class);
            return Arrays.asList(Optional.ofNullable(rateResponse).orElse(new RateTableDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }
    public List<RateTableDto> getRatesInDateRangeFromTo(String table, String startDate, String endDate){
        URI uri = UriComponentsBuilder.fromHttpUrl(rateConfig.getRateEndPoint() + "exchangerates/tables/" + table + "/" + startDate + "/" + endDate)
                .build().encode().toUri();
        try{
            RateTableDto[] rateResponse = restTemplate.getForObject(uri, RateTableDto[].class);
            return Arrays.asList(Optional.ofNullable(rateResponse).orElse(new RateTableDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }
    public RateCurrencyDto getRateAPArticularCurrency(String table, String code){
        URI uri = UriComponentsBuilder.fromHttpUrl(rateConfig.getRateEndPoint() + "exchangerates/rates/" + table + "/" + code)
                .build().encode().toUri();
        try{
            return restTemplate.getForObject(uri, RateCurrencyDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new RateCurrencyDto();
        }
    }

}
