package creatorInvoice.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "positionDetails")
public class InvoicePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int invoice_id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String price_net;
    @Column
    private String quantity;
    @Column
    private String total_price_gross;
    @Column
    private String total_price_net;
    @Column
    private Date created_at;
    @Column
    private Date updated_at;
    @Column
    private String tax;
    @Column
    private String price_gross;
    @Column
    private String price_tax;
    @Column
    private String total_price_tax;
    @Column
    private int product_id;

}
