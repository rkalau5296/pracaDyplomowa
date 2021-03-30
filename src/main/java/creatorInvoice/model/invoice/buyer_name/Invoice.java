package creatorInvoice.model.invoice.buyer_name;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Invoice {

    private String buyer_name;
    private String buyer_tax_no;
    private String buyer_post_code;
    private String buyer_city;
    private String buyer_street;
    private String buyer_first_name;
    private String buyer_country;

}
