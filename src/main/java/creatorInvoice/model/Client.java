package creatorInvoice.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String tax_no;
    @Column
    private String post_code;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String first_name;
    @Column
    private String country;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String www;
    @Column
    private String fax;
    @Column
    private Date created_at;
    @Column
    private Date updated_at;
    @Column
    private String street_no;
    @Column
    private String kind;
    @Column
    private String bank;
    @Column
    private String bank_account;
    @Column
    private String shortcut;
    @Column
    private String last_name;
    @Column
    private String mobile_phone;
    @Column
    private String register_number;
    @Column
    private String tax_no_check;

}
