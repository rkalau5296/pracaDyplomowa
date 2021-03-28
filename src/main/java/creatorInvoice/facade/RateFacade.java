package creatorInvoice.facade;

import creatorInvoice.dto.rate.RateCurrencyDto;
import creatorInvoice.dto.rate.RateTableDto;
import creatorInvoice.mapper.RateMapper;
import creatorInvoice.model.RateTable;
import creatorInvoice.service.RateDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateFacade {

    @Autowired
    private RateMapper rateMapper;
    @Autowired
    private RateDtoService rateDtoService;

    public List<RateTableDto> fetchRates(String table) {
        List<RateTable> rates = rateMapper.mapToRateTables(rateDtoService.fetchRates(table));
        return rateMapper.mapToRateTablesDto(rates);
    }
    public List<RateTableDto> fetchRatesInDateRangeFromTo(String table, String startDate, String endDate) {
        List<RateTable> rates = rateMapper.mapToRateTables(rateDtoService.fetchRatesInDateRangeFromTo(table, startDate, endDate));
        return rateMapper.mapToRateTablesDto(rates);
    }
    public RateCurrencyDto fetchRateAParticularCurrency(String table, String code) {
        return rateDtoService.fetchRateAParticularCurrency(table, code);
    }
}
