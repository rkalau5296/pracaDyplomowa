package creatorInvoice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class InvoiceConfig {
    @Value("${api.endpoint.prod}")
    private String invoiceApiEndpoint;

    @Value("${api_token}")
    private String invoiceToken;

    @Value("${token}")
    private String token;
}
