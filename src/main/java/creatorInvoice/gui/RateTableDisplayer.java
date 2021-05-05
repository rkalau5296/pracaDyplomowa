package creatorInvoice.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import creatorInvoice.controller.RateController;
import creatorInvoice.dto.rate.RateCurrencyDto;
import creatorInvoice.dto.rate.RateDto;
import creatorInvoice.dto.rate.RateTableDto;
import creatorInvoice.model.RatesCurrency;
import creatorInvoice.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Route
public class RateTableDisplayer extends VerticalLayout {

    private final ComboBox<String> comboBox;
    private final TextField currencyField;
    private final Grid<RateTableDto> tableGrid;
    private final Grid<RateDto> rateGrid;
    private final Grid<RateCurrencyDto> currencyGrid;
    private final Button buttonCurrency;
    private final RateController rateController;


    @Autowired
    public RateTableDisplayer(RateController rateController) {

        this.rateController = rateController;

        comboBox = new ComboBox<>();
        comboBox.setItems("A", "B", "C");
        comboBox.setAutoOpen(false);
        comboBox.setLabel("Podaj tabelę");
        comboBox.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> setComboBox());

        tableGrid = new Grid<>(RateTableDto.class);
        tableGrid.setMaxWidth("800px");
        tableGrid.setWidth("100%");
        tableGrid.setHeight("100px");
        tableGrid.setVisible(false);

        rateGrid = new Grid<>(RateDto.class);
        rateGrid.setMaxWidth("800px");
        rateGrid.setWidth("100%");
        rateGrid.setVisible(false);

        currencyField = new TextField("Podaj walutę");
        currencyField.setVisible(false);

        buttonCurrency = new Button("Pobierz podaną walutę");
        buttonCurrency.setVisible(false);
        buttonCurrency.addClickListener(clickEvent -> buttonCurrency());

        currencyGrid = new Grid<>(RateCurrencyDto.class);
        currencyGrid.setMaxWidth("800px");
        currencyGrid.setWidth("100%");
        currencyGrid.setHeight("100px");
        currencyGrid.setVisible(false);


        add(comboBox, tableGrid, rateGrid, currencyField, buttonCurrency, currencyGrid);
    }

    public void setComboBox(){

        tableGrid.setVisible(true);
        rateGrid.setVisible(true);
        currencyField.setVisible(true);
        buttonCurrency.setVisible(true);

        RateTableDto rateTable = new RateTableDto();
        rateTable.setTable(comboBox.getValue());
        List<RateTableDto> rateTableDtos = rateController.getRates(comboBox.getValue());
        rateTable.setNo(rateTableDtos.get(0).getNo());
        rateTable.setEffectiveDate(rateTableDtos.get(0).getEffectiveDate());
        List<RateDto> rateDtoList = rateTableDtos.get(0).getRates();
        tableGrid.setItems(rateTable);
        rateGrid.setItems(rateDtoList);
    }

    public void buttonCurrency(){
        try {
            currencyGrid.setVisible(true);
            RateCurrencyDto rateCurrencyDto = new RateCurrencyDto();
            rateCurrencyDto.setCode(currencyField.getValue());
            rateCurrencyDto.setTable(comboBox.getValue());
            RateCurrencyDto currencyDto = rateController.getRateAParticularCurrency(comboBox.getValue(), currencyField.getValue());
            rateCurrencyDto.setCurrency(currencyDto.getCurrency());
            rateCurrencyDto.setRates(currencyDto.getRates());

            List<RatesCurrency> ratesCurrencies = currencyDto.getRates();

            RatesCurrency ratesCurrency = new RatesCurrency();
            ratesCurrency.setEffectiveDate(ratesCurrencies.get(0).getEffectiveDate());
            ratesCurrency.setMid(ratesCurrencies.get(0).getMid());
            ratesCurrency.setNo(ratesCurrencies.get(0).getNo());

            currencyGrid.setItems(rateCurrencyDto);
        }
        catch (Exception e){
            Notification notification = Notification.show(
                    "Nie ma takiego kodu waluty");
            notification.setPosition(Notification.Position.TOP_CENTER);
            add(notification);
        }
    }
    //TODO dodać datapicker i sprawdzić dlaczego nie się nie kompiluje.
}
