package creatorInvoice.model.invoice.position;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Invoice {

    private Long id;
    private int payment_to_kind;
    private int client_id;
    private List<Position> positions;
}
