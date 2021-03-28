package creatorInvoice.service;

import creatorInvoice.dto.rate.RateCurrencyDto;
import creatorInvoice.dto.rate.RateTableDto;
import creatorInvoice.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateDtoService {

    @Autowired
    private Url url;

    public List<RateTableDto> fetchRates(String table) {
        return url.getRates(table);
    }
    public List<RateTableDto> fetchRatesInDateRangeFromTo(String table, String startDate, String endDate) {
        return url.getRatesInDateRangeFromTo(table, startDate, endDate);
    }
    public RateCurrencyDto fetchRateAParticularCurrency(String table, String code) {
        return url.getRateAPArticularCurrency(table, code);
    }
}
