package creatorInvoice.dto.rate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RateDto {

    private String currency;
    private String code;
    private double mid;
}

