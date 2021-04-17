package creatorInvoice.dto.rate;

import creatorInvoice.model.RatesCurrency;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RateCurrencyDto {

    String table;
    String currency;
    String code;
    List<RatesCurrency> rates;

}
