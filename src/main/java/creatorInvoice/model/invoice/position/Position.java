package creatorInvoice.model.invoice.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Position {

    private Long id;
    private int product_id;
    private int quantity;
}
