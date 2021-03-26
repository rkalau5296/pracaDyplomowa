package creatorInvoice.dto.invoice;

import creatorInvoice.model.InvoicePosition;
import lombok.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvoiceDto {
  
    private int id;
    
    private String number;
    
    private String place;
    
    private String sell_date;
    
    private String payment_type;
    
    private String price_net;
    
    private String price_gross;
    
    private String currency;
    
    private String status;
    
    private String description;
    
    private String seller_name;
    
    private String seller_tax_no;
    
    private String seller_street;
    
    private String seller_post_code;
    
    private String seller_city;
    
    private String seller_country;
    
    private String seller_email;
    
    private String seller_phone;
    
    private String seller_fax;
    
    private String seller_www;
    
    private String seller_person;
    
    private String seller_bank;
    
    private String seller_bank_account;
    
    private String buyer_name;
    
    private String buyer_tax_no;
    
    private String buyer_post_code;
    
    private String buyer_city;
    
    private String buyer_street;
    
    private String buyer_first_name;
    
    private String buyer_country;
    
    private Date created_at;
    
    private Date updated_at;
    
    private int client_id;
    
    private String payment_to;
    
    private String product_cache;
    
    private int product_id;

    private List<InvoicePosition> positions;
    
}
