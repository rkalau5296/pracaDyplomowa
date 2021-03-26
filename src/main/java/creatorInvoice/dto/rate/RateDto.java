package creatorInvoice.dto.rate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {
    String currency;
    String code;
    double mid;
}

