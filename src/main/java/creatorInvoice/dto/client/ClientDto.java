package creatorInvoice.dto.client;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ClientDto {

    
    private int id;
    
    private String name;
    
    private String tax_no;
    
    private String post_code;
    
    private String city;
    
    private String street;
    
    private String first_name;
    
    private String country;
    
    private String email;
    
    private String phone;
    
    private String www;
    
    private String fax;
    
    private Date created_at;
    
    private Date updated_at;
    
    private String street_no;
    
    private String kind;
    
    private String bank;
    
    private String bank_account;
    
    private String shortcut;
    
    private String last_name;
    
    private String mobile_phone;
    
    private String register_number;
    
    private String tax_no_check;

}

