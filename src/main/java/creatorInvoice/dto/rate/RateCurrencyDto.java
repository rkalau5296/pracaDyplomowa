package creatorInvoice.dto.rate;

import creatorInvoice.model.RatesCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateCurrencyDto {

    private String table;
    private String currency;
    private String code;
    private List<RatesCurrency> rates;
}
