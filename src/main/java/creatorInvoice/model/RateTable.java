package creatorInvoice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateTable {

    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;
}
