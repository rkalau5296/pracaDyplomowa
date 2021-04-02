package creatorInvoice.dto.invoice.modify.position.addnew;

import creatorInvoice.dto.invoice.modify.position.InvoicePositionDto;
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

    private List<InvoicePositionDto> positions;

}
