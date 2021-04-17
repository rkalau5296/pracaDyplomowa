package creatorInvoice.dto.rate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class RateTableDto {


    private String table;
    private String no;
    private String effectiveDate;
    private List<RateDto> rates;
}
