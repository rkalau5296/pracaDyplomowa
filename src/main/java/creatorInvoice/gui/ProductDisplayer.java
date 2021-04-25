package creatorInvoice.gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import creatorInvoice.url.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Route
public class ProductDisplayer extends VerticalLayout {

    private final ProductController productController;
    private final Grid<ProductDto> productGrid;
    private final IntegerField integerField;
    private final InvoiceConfig invoiceConfig;

    @Autowired
    public ProductDisplayer(InvoiceConfig invoiceConfig, ProductController productController){

        this.productController = productController;
        this.invoiceConfig=invoiceConfig;
        productGrid = new Grid<>(ProductDto.class);
        productGrid.setWidth("3500px");
        productGrid.setColumnReorderingAllowed(true);
        productGrid.addComponentColumn(item-> new Button("Usuń", buttonClickEvent -> {
            deleteDialog(item.getId()).open();
        }));

        productGrid.addComponentColumn(item-> new Button("Edytuj", buttonClickEvent -> {
            updateProductDialog(item).open();
        }));

        Button getProductButton = new Button("Pobierz produkty");
        getProductButton.addClickListener(buttonClickEvent -> addProductsToGrid());

        integerField = new IntegerField("Podaj id produktu");
        Button getProductByIdButton = new Button("Pobierz produkt po id");

        getProductByIdButton.addClickListener(buttonClickEvent -> {
            if(integerField.getValue()==null){
                Notification notification = Notification.show(
                        "Nie wprowadzono nr id. Puste pole. Wprowadź nr id.");
                add(notification);
            }
            addProductsByIdToGrid(integerField.getValue());
        });




        Button newProduct = new Button("Nowy product");
        newProduct.addClickListener(buttonClickEvent -> addNewVehicleDialog().open());

        MyCustomLayout upperLayout = new MyCustomLayout();
        upperLayout.addItemWithLabel("", getProductByIdButton);
        upperLayout.addItemWithLabel("", getProductButton);
        upperLayout.addItemWithLabel("", integerField);






        MyCustomLayout lowerLayout = new MyCustomLayout();
        lowerLayout.addItemWithLabel("", newProduct);

        add(upperLayout, productGrid, lowerLayout);
    }

    public void addProductsToGrid(){
        productGrid.setItems(productController.getProducts());
    }

    public void addProductsByIdToGrid(long id) {
        try{
            productGrid.setItems(productController.getProduct(id));
        }
        catch (Exception e){
            Notification notification = Notification.show(
                    "Id z poza zakresu, nie ma takiego id. Podaj prawidłowe id.");
            add(notification);
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
        if(productDto.getDescription()!=null)
        description.setValue(productDto.getDescription());
        else{
            description.setValue("Puste");
        }

        TextField price_net = new TextField("Cena netto");
        price_net.setValue(productDto.getPrice_net());

        TextField tax = new TextField("Wysokość podatku");
        tax.setValue(productDto.getTax());

        TextField price_gross = new TextField("Cena brutto");
        price_gross.setValue(productDto.getPrice_gross());

        TextField price_tax = new TextField("Kwota podatku");
        price_tax.setValue(productDto.getPrice_tax());

        TextField form_name = new TextField("Nazwa produktu");
        form_name.setValue(productDto.getForm_name());

        TextField form_description = new TextField("Opis produktu");
        form_description.setValue(productDto.getForm_description());
        if(productDto.getForm_description()!=null)
            form_description.setValue(productDto.getForm_description());
        else{
            form_description.setValue("Puste");
        }


        Button save = new Button("Save", buttonClickEvent -> {
//            if(name.getValue()==null)
//            {
//                Notification notification = Notification.show(
//                        "Nie wprowadzono wszystkich danych. Wypełnij wszystkie pola.");
//                add(notification);
//            }
            ProductDto product = new ProductDto(
                    id.getValue(),
                    name.getValue(),
                    description.getValue(),
                    price_net.getValue(),
                    tax.getValue(),
                    productDto.getCreated_at(),
                    productDto.getUpdated_at(),
                    price_gross.getValue(),
                    price_tax.getValue(),
                    form_name.getValue(),
                    form_description.getValue()

            );

            productController.updateProduct(new AddProductDto(invoiceConfig.getToken(), product), (long)productDto.getId());
            addProductsToGrid();
            dialog.close();
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
        layout.addItemWithLabel("",form_name);
        layout.addItemWithLabel("",form_description);
        layout.addItemWithLabel("",abort);
        layout.addItemWithLabel("",save);

        dialog.add(layout);

        return dialog;
    }
    public Dialog addNewVehicleDialog() {

        Dialog dialog = new Dialog(new Text("Dodawanie nowego produktu"));

        IntegerField integerField = new IntegerField("Id");
        TextField brand = new TextField("Brand");
        TextField color = new TextField("Color");
        TextField model = new TextField("Model");

        Button save = new Button("Save", buttonClickEvent -> {
            if(integerField.getValue()==null|| brand.getValue()==null || color.getValue()==null||model.getValue()==null)
            {
                Notification notification = Notification.show(
                        "Nie wprowadzono wszystkich danych. Wypełnij wszystkie pola.");
                add(notification);
            }
            //url.postProduct(new Product(integerField.getValue(), brand.getValue(), color.getValue(), model.getValue()));
            addProductsToGrid();
            dialog.close();
        });
        Button cancel = new Button("Cancel", buttonClickEvent -> {
            dialog.close();
        });

        MyCustomLayout upperLayout = new MyCustomLayout();

        upperLayout.addItemWithLabel("",integerField);
        upperLayout.addItemWithLabel("",brand);
        upperLayout.addItemWithLabel("",color);
        upperLayout.addItemWithLabel("",model);
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
            addProductsToGrid();
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
