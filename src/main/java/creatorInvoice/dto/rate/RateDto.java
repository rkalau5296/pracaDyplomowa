package creatorInvoice.dto.rate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto {

    private String currency;
    private String code;
    private double mid;
}

