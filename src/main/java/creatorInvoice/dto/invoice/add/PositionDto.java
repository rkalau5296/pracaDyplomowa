package creatorInvoice.dto.invoice.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PositionDto {

    private Long id;
    private int product_id;
    private int quantity;
}
