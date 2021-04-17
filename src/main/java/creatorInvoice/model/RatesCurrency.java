package creatorInvoice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatesCurrency {

    String no;
    private String effectiveDate;
    double mid;

    @Override
    public String toString() {
        return "" + mid;
    }
}
