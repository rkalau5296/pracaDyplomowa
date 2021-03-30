package creatorInvoice.model.invoice.position.modify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Invoice {

    private Long id;
    private List<Position> positions;

}
