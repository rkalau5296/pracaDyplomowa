package creatorInvoice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RateTable {

    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;
}
