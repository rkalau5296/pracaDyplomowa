package creatorInvoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

        private String currency;
        private String code;
        private double mid;
}
