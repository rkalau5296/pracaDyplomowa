package creatorInvoice.dto.invoice.modify.position;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class InvoiceDto {

    private Long id;
    private List<InvoicePositionDto> positions;

}
