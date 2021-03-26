package creatorInvoice.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String number;
    @Column
    private String place;
    @Column
    private String sell_date;
    @Column
    private String payment_type;
    @Column
    private String price_net;
    @Column
    private String price_gross;
    @Column
    private String currency;
    @Column
    private String status;
    @Column
    private String description;
    @Column
    private String seller_name;
    @Column
    private String seller_tax_no;
    @Column
    private String seller_street;
    @Column
    private String seller_post_code;
    @Column
    private String seller_city;
    @Column
    private String seller_country;
    @Column
    private String seller_email;
    @Column
    private String seller_phone;
    @Column
    private String seller_fax;
    @Column
    private String seller_www;
    @Column
    private String seller_person;
    @Column
    private String seller_bank;
    @Column
    private String seller_bank_account;
    @Column
    private String buyer_name;
    @Column
    private String buyer_tax_no;
    @Column
    private String buyer_post_code;
    @Column
    private String buyer_city;
    @Column
    private String buyer_street;
    @Column
    private String buyer_first_name;
    @Column
    private String buyer_country;
    @Column
    private Date created_at;
    @Column
    private Date updated_at;
    @Column
    private int client_id;
    @Column
    private String payment_to;
    @Column
    private String product_cache;
    @Column
    private int product_id;
    @Column
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvoicePosition> positions;
}



