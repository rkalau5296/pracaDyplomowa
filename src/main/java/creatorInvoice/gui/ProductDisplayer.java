package creatorInvoice.gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import creatorInvoice.config.InvoiceConfig;
import creatorInvoice.controller.ProductController;
import creatorInvoice.dto.product.AddProductDto;
import creatorInvoice.dto.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
@Route
public class ProductDisplayer extends VerticalLayout {

    private final ProductController productController;
    private final Grid<ProductDto> productGrid;
    private final IntegerField integerField;
    private final InvoiceConfig invoiceConfig;
    private final TextField name;
    private final TextField description;

    @Autowired
    public ProductDisplayer(InvoiceConfig invoiceConfig, ProductController productController){

        this.productController = productController;
        this.invoiceConfig=invoiceConfig;
        productGrid = new Grid<>(ProductDto.class);
        productGrid.removeAllColumns();
        productGrid.addColumn(ProductDto::getId).setHeader("Id");
        productGrid.addColumn(ProductDto::getName).setHeader("Name");
        productGrid.addColumn(ProductDto::getDescription).setHeader("Description");
        productGrid.addColumn(ProductDto::getPrice_net).setHeader("Price_net");
        productGrid.addColumn(ProductDto::getPrice_tax).setHeader("Price_tax");
        productGrid.addColumn(ProductDto::getTax).setHeader("Tax");
        productGrid.addColumn(ProductDto::getPrice_gross).setHeader("Price_gross");
        productGrid.addColumn(ProductDto::getCreated_at).setHeader("Created_at");
        productGrid.addColumn(ProductDto::getUpdated_at).setHeader("Update_at");
        productGrid.setWidth("1700px");
        productGrid.addComponentColumn(item-> new Button("Usuń", buttonClickEvent -> {
            deleteDialog(item.getId()).open();
        }));

        productGrid.addComponentColumn(item-> new Button("Edytuj", buttonClickEvent -> {
            updateProductDialog(item).open();
        }));

        Button getProductButton = new Button("Pobierz wszystkie produkty");
        getProductButton.addClickListener(buttonClickEvent -> fetchProductsAndAddToGrid());

        integerField = new IntegerField("Podaj id produktu");
        Button getProductByIdButton = new Button("Pobierz produkt po id");
        getProductByIdButton.addClickListener(buttonClickEvent ->{

                if(integerField.isEmpty())
                {
                    Notification notification = Notification.show(
                            "Puste pole. Podaj prawidłowe id.");
                    notification.setPosition(Notification.Position.TOP_CENTER);
                    add(notification);
                }else{
                fetchProductsByIdAndAddToGrid(integerField.getValue(), productController.getProduct((long)integerField.getValue()));
                }
        });

        Button newProduct = new Button("Nowy product");
        newProduct.addClickListener(buttonClickEvent -> addNewProductDialog().open());

        name = new TextField("Nazwa");
        Button getProductByNameButton = new Button("Pobierz produkt po nazwie");
        getProductByNameButton.addClickListener(event -> fetchProductByNameAndToGrid(name.getValue(), productController.getProducts()));

        description = new TextField("Opis");
        Button getProductByDescriptionButton = new Button("Pobierz produkt po opisie");
        getProductByDescriptionButton.addClickListener(event -> fetchProductByDescriptionAndToGrid(description.getValue(), productController.getProducts()));

        MyCustomLayout upperLayout = new MyCustomLayout();

        upperLayout.addItemWithLabel("", integerField);
        upperLayout.addItemWithLabel("", getProductByIdButton);
        upperLayout.addItemWithLabel("", name);
        upperLayout.addItemWithLabel("", getProductByNameButton);
        upperLayout.addItemWithLabel("", description);
        upperLayout.addItemWithLabel("", getProductByDescriptionButton);
        upperLayout.addItemWithLabel("", getProductButton);

        MyCustomLayout lowerLayout = new MyCustomLayout();
        lowerLayout.addItemWithLabel("", newProduct);

        add(upperLayout, productGrid, lowerLayout);
    }

    public void fetchProductsAndAddToGrid(){

        List<ProductDto> productDtos = productController.getProducts();
        productDtos.sort(Comparator.comparingInt(ProductDto::getId));

        productGrid.setItems(productDtos);
    }

    public void fetchProductsByIdAndAddToGrid(long id, ProductDto productDto) {


        if (productDto.getId()!=id){
            Notification notification = Notification.show(
                    "Id z poza zakresu, nie ma takiego id. Podaj prawidłowe id.");
            notification.setPosition(Notification.Position.TOP_CENTER);
            add(notification);
        }
       else {
            productGrid.setItems(productDto);
        }
    }

    public void fetchProductByNameAndToGrid(String name, List<ProductDto> products) {

        List<ProductDto> productListByName = products.stream()
                .filter(productDto -> productDto.getName().equals(name))
                .sorted(Comparator.comparingInt(ProductDto::getId))
                .collect(Collectors.toList());

        if(productListByName.isEmpty()){
            Notification notification = Notification.show(
                    "Błedna nazwa, nie ma produktu o takiej nazwie. Podaj prawidłową nazwę.");
            notification.setPosition(Notification.Position.TOP_CENTER);
            add(notification);
        }else {
            productGrid.setItems(productListByName);
        }

    }
    public void fetchProductByDescriptionAndToGrid(String desc, List<ProductDto> products) {
        //TO DO - sprawdzić, dlaczego nie porównuje i nie zwraca productDto
        List<ProductDto> productListByDescription = products.stream()
                .filter(productDto -> productDto.getDescription()==desc)
                .sorted(Comparator.comparingInt(ProductDto::getId))
                .collect(Collectors.toList());

        if(productListByDescription.isEmpty()){
            Notification notification = Notification.show(
                    "Błedny opis, nie ma produktu o takim opisie. Podaj prawidłowy opis.");
            notification.setPosition(Notification.Position.TOP_CENTER);
            add(notification);
        }else {
            productGrid.setItems(productListByDescription);
        }
    }

    public Dialog updateProductDialog(ProductDto productDto) {


        Dialog dialog = new Dialog(new Text("Edytuj produkt"));

        IntegerField id = new IntegerField("Id produktu");
        id.setValue(productDto.getId());
        id.setEnabled(false);

        TextField name = new TextField("Nazwa");
        name.setValue(productDto.getName());

        TextField description = new TextField("Opis");
        if(productDto.getDescription()==null)
            description.setValue("");
        else
            description.setValue(productDto.getDescription());

        TextField price_net = new TextField("Cena netto");
        if(productDto.getPrice_net()==null)
            price_net.setValue("");
        else
            price_net.setValue(productDto.getPrice_net());

        TextField tax = new TextField("Wysokość podatku");
        if(productDto.getTax()==null)
            tax.setValue("");
        else
            tax.setValue(productDto.getTax());

        TextField price_gross = new TextField("Cena brutto");
        if(productDto.getPrice_gross()==null)
            price_gross.setValue("");
        else
            price_gross.setValue(productDto.getPrice_gross());


        TextField price_tax = new TextField("Kwota podatku");
        if(productDto.getPrice_tax()==null)
            price_tax.setValue("");
        else
            price_tax.setValue(productDto.getPrice_tax());


        Button save = new Button("Save", buttonClickEvent -> {

            ProductDto product = new ProductDto(
                    id.getValue(),
                    name.getValue(),
                    description.getValue(),
                    price_net.getValue(),
                    tax.getValue(),
                    productDto.getCreated_at(),
                    productDto.getUpdated_at(),
                    price_gross.getValue(),
                    price_tax.getValue()

            );

            if(name.getValue().equals(""))
            {
                Notification notification = Notification.show(
                        "Pole 'nazwa' nie może być puste. Podaj nazwę produktu");
                notification.setPosition(Notification.Position.TOP_CENTER);
                add(notification);
            }
            else {
                productController.updateProduct(new AddProductDto(invoiceConfig.getToken(), product), (long) productDto.getId());
                fetchProductsAndAddToGrid();
                dialog.close();
            }
        });
        Button abort = new Button("Cancel", buttonClickEvent -> {
            dialog.close();
        });

        MyCustomLayout layout = new MyCustomLayout();

        layout.addItemWithLabel("", id);
        layout.addItemWithLabel("",name);
        layout.addItemWithLabel("",description);
        layout.addItemWithLabel("",price_net);
        layout.addItemWithLabel("",tax);
        layout.addItemWithLabel("",price_gross);
        layout.addItemWithLabel("",price_tax);
        layout.addItemWithLabel("",abort);
        layout.addItemWithLabel("",save);

        dialog.add(layout);

        return dialog;
    }

    public Dialog addNewProductDialog() {

        Dialog dialog = new Dialog(new Text("Dodawanie nowego produktu"));

        IntegerField integerField = new IntegerField("Id");
        integerField.setValue(0);

        TextField name = new TextField("Nazwa");

        TextField description = new TextField("Opis");

        TextField price_net = new TextField("Cena netto");

        TextField tax = new TextField("Wysokość podatku");

        TextField price_gross = new TextField("Cena brutto");

        TextField price_tax = new TextField("Kwota podatku");

        Button save = new Button("Save", buttonClickEvent -> {

            ProductDto product = new ProductDto(
                    integerField.getValue(),
                    name.getValue(),
                    description.getValue(),
                    price_net.getValue(),
                    tax.getValue(),
                    new Date(),
                    new Date(),
                    price_gross.getValue(),
                    price_tax.getValue()

            );
            if(name.getValue().equals(""))
            {
                Notification notification = Notification.show(
                        "Pole 'nazwa' nie może być puste. Podaj nazwę produktu");
                notification.setPosition(Notification.Position.TOP_CENTER);
                add(notification);
            }
            else {
                productController.createProduct(new AddProductDto(invoiceConfig.getToken(), product));
                fetchProductsAndAddToGrid();
                dialog.close();
            }
        });
        Button cancel = new Button("Cancel", buttonClickEvent -> {
            dialog.close();
        });

        MyCustomLayout upperLayout = new MyCustomLayout();

        upperLayout.addItemWithLabel("", name);
        upperLayout.addItemWithLabel("", description);
        upperLayout.addItemWithLabel("", price_net);
        upperLayout.addItemWithLabel("", tax);
        upperLayout.addItemWithLabel("", price_gross);
        upperLayout.addItemWithLabel("", price_tax);
        upperLayout.addItemWithLabel("", cancel);
        upperLayout.addItemWithLabel("",save);

        dialog.add(upperLayout );

        return dialog;
    }

    public Dialog deleteDialog(int id) {

        Dialog dialog = new Dialog(new Text("Usuń produkt"));
        Text deleteTextConfirmation = new Text("Czy na pewno chcesz usunąć ten produkt?");
        Text emptyText = new Text("   ");
        Button delete = new Button("Usuń", buttonClickEvent -> {
            productController.deleteProduct((long)id);
            fetchProductsAndAddToGrid();
            dialog.close();
        });
        Button cancel = new Button("Cancel", buttonClickEvent -> {
            dialog.close();
        });

        MyCustomLayout layout = new MyCustomLayout();
        layout.addItemWithLabel("", deleteTextConfirmation);
        layout.addItemWithLabel("", emptyText);
        layout.addItemWithLabel("", cancel);
        layout.addItemWithLabel("", delete);

        dialog.add(layout);

        return dialog;
    }

}
