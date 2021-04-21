package creatorInvoice.gui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

import static com.vaadin.flow.component.UI.getCurrent;

@Route("")
@PreserveOnRefresh
public class AppLayoutWithNavbarMenu extends AppLayout {

    @Autowired
    private final RateTableDisplayer rateTableDisplayer;

    public AppLayoutWithNavbarMenu(RateTableDisplayer rateTableDisplayer) {

        this.rateTableDisplayer = rateTableDisplayer;
        Image img = new Image("frontend/pw.png", "");
        img.setHeight("44px");
        final boolean touchOptimized = true;
        addToNavbar(touchOptimized, new DrawerToggle(), img);
//        Tab invoices = new Tab("Faktury");
//        Tab clients = new Tab("Klienci");
//
        Tab products = new Tab("Produkty");
        Div productsPage = new Div();
        productsPage.setText("Produkty");
        productsPage.setVisible(false);


        Tab rates = new Tab("Kursy");
        Div ratesPage = new Div();
        ratesPage.add(rateTableDisplayer);
        ratesPage.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(products, productsPage);
        tabsToPages.put(rates, ratesPage);

        Tabs tabs = new Tabs( products, rates);
        Div pages = new Div(productsPage, ratesPage);

        tabs.addSelectedChangeListener(event -> {
            tabsToPages.values().forEach(component->component.setVisible(false));
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            //getCurrent().getPage().reload();
        });

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setHeight("330px");

        setContent(pages);
        addToDrawer(tabs);

        //addToNavbar(pages);
        //add(ratesPage);
    }


}