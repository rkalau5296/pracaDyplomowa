package creatorInvoice.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String price_net;
    @Column
    private String tax;
    @Column
    private Date created_at;
    @Column
    private Date updated_at;
    @Column
    private String price_gross;
    @Column
    private String price_tax;

}
