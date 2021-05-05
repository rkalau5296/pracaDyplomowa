package creatorInvoice.gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import creatorInvoice.config.InvoiceConfig;
import creatorInvoice.controller.ClientController;
import creatorInvoice.dto.client.AddClientDto;
import creatorInvoice.dto.client.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Route
public class ClientDisplayer extends VerticalLayout {

    private final ClientController clientController;
    private final Grid<ClientDto> clientGrid;
    private final IntegerField integerField;
    private final InvoiceConfig invoiceConfig;
    private final TextField name;


    @Autowired
    public ClientDisplayer(ClientController clientController, InvoiceConfig invoiceConfig) {

        this.clientController = clientController;
        this.invoiceConfig=invoiceConfig;
        clientGrid = new Grid<>(ClientDto.class);
        clientGrid.removeAllColumns();
        clientGrid.addColumn(ClientDto::getId).setHeader("Id");
        clientGrid.addColumn(ClientDto::getName).setHeader("Name");
        clientGrid.addColumn(ClientDto::getFirst_name).setHeader("Description");
        clientGrid.addColumn(ClientDto::getLast_name).setHeader("Price_net");
        clientGrid.addColumn(ClientDto::getCity).setHeader("Price_tax");
        clientGrid.addColumn(ClientDto::getEmail).setHeader("Tax");
        clientGrid.addColumn(ClientDto::getMobile_phone).setHeader("Price_gross");
        clientGrid.addColumn(ClientDto::getCreated_at).setHeader("Created_at");
        clientGrid.addColumn(ClientDto::getUpdated_at).setHeader("Update_at");
        clientGrid.setWidth("1700px");
        clientGrid.addComponentColumn(item-> new Button("Usuń", buttonClickEvent -> {
            deleteDialog(item.getId()).open();
        }));

        clientGrid.addComponentColumn(item-> new Button("Edytuj", buttonClickEvent -> {
            updateClientDialog(item).open();
        }));

        Button getClientButton = new Button("Pobierz wszystkich klientów");
        getClientButton.addClickListener(buttonClickEvent -> fetchClientAndAddToGrid());

        integerField = new IntegerField("Podaj id klienta");
        Button getClientByIdButton = new Button("Pobierz klienta po id");
        getClientByIdButton.addClickListener(buttonClickEvent ->{

            if(integerField.isEmpty())
            {
                Notification notification = Notification.show(
                        "Puste pole. Podaj prawidłowe id.");
                notification.setPosition(Notification.Position.TOP_CENTER);
                add(notification);
            }else{
                fetchClientsByIdAndAddToGrid(integerField.getValue(), clientController.getClient((long)integerField.getValue()));
            }
        });

        Button newClient = new Button("Nowy klient");
        newClient.addClickListener(buttonClickEvent -> addNewClientDialog().open());

        name = new TextField("Nazwa");
        Button getClientByNameButton = new Button("Pobierz klienta po nazwie");
        getClientByNameButton.addClickListener(event -> fetchClientsByNameAndToGrid(name.getValue(), clientController.getClients()));



        MyCustomLayout upperLayout = new MyCustomLayout();

        upperLayout.addItemWithLabel("", integerField);
        upperLayout.addItemWithLabel("", getClientByIdButton);
        upperLayout.addItemWithLabel("", name);
        upperLayout.addItemWithLabel("", getClientByNameButton);
        upperLayout.addItemWithLabel("", getClientButton);

        MyCustomLayout lowerLayout = new MyCustomLayout();
        lowerLayout.addItemWithLabel("", newClient);

        add(upperLayout, clientGrid, lowerLayout);
    }
    public void fetchClientAndAddToGrid(){

        List<ClientDto> clientDtos = clientController.getClients();
        clientDtos.sort(Comparator.comparingInt(ClientDto::getId));

        clientGrid.setItems(clientDtos);
    }

    public void fetchClientsByIdAndAddToGrid(long id, ClientDto clientDto) {

        if (clientDto.getId()!=id){
            Notification notification = Notification.show(
                    "Id z poza zakresu, nie ma takiego id. Podaj prawidłowe id.");
            notification.setPosition(Notification.Position.TOP_CENTER);
            add(notification);
        }
        else {
            clientGrid.setItems(clientDto);
        }
    }

    public void fetchClientsByNameAndToGrid(String name, List<ClientDto> clientDtos) {

        List<ClientDto> clientsListByName = clientDtos.stream()
                .filter(clientDto -> clientDto.getName().equals(name))
                .sorted(Comparator.comparingInt(ClientDto::getId))
                .collect(Collectors.toList());

        if(clientsListByName.isEmpty()){
            Notification notification = Notification.show(
                    "Błedna nazwa, nie ma klienta o takiej nazwie. Podaj prawidłową nazwę.");
            notification.setPosition(Notification.Position.TOP_CENTER);
            add(notification);
        }else {
            clientGrid.setItems(clientsListByName);
        }

    }


    public Dialog updateClientDialog(ClientDto clientDto) {


        Dialog dialog = new Dialog(new Text("Edytuj klienta"));

        IntegerField id = new IntegerField("Id klienta");
        id.setValue(clientDto.getId());
        id.setEnabled(false);

        TextField name = new TextField("Nazwa");
        name.setValue(clientDto.getName());

        TextField tax_no = new TextField("tax_no");
        if(clientDto.getTax_no()==null)
            tax_no.setValue("");
        else
            tax_no.setValue(clientDto.getTax_no());

        TextField post_code = new TextField("post_code");
        if(clientDto.getPost_code()==null)
            post_code.setValue("");
        else
            post_code.setValue(clientDto.getPost_code());

        TextField city = new TextField("city");
        if(clientDto.getCity()==null)
            city.setValue("");
        else
            city.setValue(clientDto.getCity());

        TextField street = new TextField("street");
        if(clientDto.getStreet()==null)
            street.setValue("");
        else
            street.setValue(clientDto.getStreet());


        TextField first_name = new TextField("first_name");
        if(clientDto.getFirst_name()==null)
            first_name.setValue("");
        else
            first_name.setValue(clientDto.getFirst_name());

        TextField country = new TextField("country");
        if(clientDto.getCountry()==null)
            country.setValue("");
        else
            country.setValue(clientDto.getCountry());

        TextField email = new TextField("email");
        if(clientDto.getEmail()==null)
            email.setValue("");
        else
            email.setValue(clientDto.getEmail());

        TextField phone = new TextField("phone");
        if(clientDto.getPhone()==null)
            phone.setValue("");
        else
            phone.setValue(clientDto.getPhone());

        TextField www = new TextField("www");
        if(clientDto.getWww()==null)
            www.setValue("");
        else
            www.setValue(clientDto.getWww());

        TextField fax = new TextField("fax");
        if(clientDto.getFax()==null)
            fax.setValue("");
        else
            fax.setValue(clientDto.getFax());

        TextField street_no = new TextField("street_no");
        if(clientDto.getStreet_no()==null)
            street_no.setValue("");
        else
            street_no.setValue(clientDto.getStreet_no());

        TextField kind = new TextField("kind");
        if(clientDto.getKind()==null)
            kind.setValue("");
        else
            kind.setValue(clientDto.getKind());

        TextField bank = new TextField("bank");
        if(clientDto.getBank()==null)
            bank.setValue("");
        else
            bank.setValue(clientDto.getBank());

        TextField bank_account = new TextField("bank_account");
        if(clientDto.getBank_account()==null)
            bank_account.setValue("");
        else
            bank_account.setValue(clientDto.getBank_account());

        TextField shortcut = new TextField("shortcut");
        if(clientDto.getShortcut()==null)
            shortcut.setValue("");
        else
            shortcut.setValue(clientDto.getShortcut());

        TextField last_name = new TextField("last_name");
        if(clientDto.getLast_name()==null)
            last_name.setValue("");
        else
            last_name.setValue(clientDto.getLast_name());

        TextField mobile_phone = new TextField("mobile_phone");
        if(clientDto.getMobile_phone()==null)
            mobile_phone.setValue("");
        else
            mobile_phone.setValue(clientDto.getMobile_phone());

        TextField register_number = new TextField("register_number");
        if(clientDto.getRegister_number()==null)
            register_number.setValue("");
        else
            register_number.setValue(clientDto.getRegister_number());

        TextField tax_no_check = new TextField("tax_no_check");
        if(clientDto.getTax_no_check()==null)
            tax_no_check.setValue("");
        else
            tax_no_check.setValue(clientDto.getTax_no_check());

        Button save = new Button("Save", buttonClickEvent -> {

            ClientDto client = new ClientDto(
                    id.getValue(),
                    name.getValue(),
                    tax_no.getValue(),
                    post_code.getValue(),
                    city.getValue(),
                    street.getValue(),
                    first_name.getValue(),
                    country.getValue(),
                    email.getValue(),
                    phone.getValue(),
                    www.getValue(),
                    fax.getValue(),
                    clientDto.getCreated_at(),
                    clientDto.getUpdated_at(),
                    street_no.getValue(),
                    kind.getValue(),
                    bank.getValue(),
                    bank_account.getValue(),
                    shortcut.getValue(),
                    last_name.getValue(),
                    mobile_phone.getValue(),
                    register_number.getValue(),
                    tax_no_check.getValue()
            );

            if(name.getValue().equals(""))
            {
                Notification notification = Notification.show(
                        "Pole 'nazwa' nie może być puste. Podaj nazwę klienta");
                notification.setPosition(Notification.Position.TOP_CENTER);
                add(notification);
            }
            else {
                clientController.updateClient(new AddClientDto(invoiceConfig.getToken(), client), (long) clientDto.getId());
                fetchClientAndAddToGrid();
                dialog.close();
            }
        });
        Button abort = new Button("Cancel", buttonClickEvent -> {
            dialog.close();
        });

        MyCustomLayout layout = new MyCustomLayout();

        layout.addItemWithLabel("", id);
        layout.addItemWithLabel("",name);
        layout.addItemWithLabel("",tax_no);
        layout.addItemWithLabel("",post_code);
        layout.addItemWithLabel("",city);
        layout.addItemWithLabel("",street);
        layout.addItemWithLabel("",first_name);
        layout.addItemWithLabel("",country);
        layout.addItemWithLabel("",email);
        layout.addItemWithLabel("",phone);
        layout.addItemWithLabel("",street_no);
        layout.addItemWithLabel("",kind);
        layout.addItemWithLabel("",bank);
        layout.addItemWithLabel("",bank_account);
        layout.addItemWithLabel("",shortcut);
        layout.addItemWithLabel("",last_name);
        layout.addItemWithLabel("",mobile_phone);
        layout.addItemWithLabel("",register_number);
        layout.addItemWithLabel("",tax_no_check);
        layout.addItemWithLabel("",abort);
        layout.addItemWithLabel("",save);

        dialog.add(layout);

        return dialog;
    }

    public Dialog addNewClientDialog() {

        Dialog dialog = new Dialog(new Text("Dodawanie nowego klienta"));

        IntegerField id = new IntegerField("Id");
        integerField.setValue(0);

        TextField name = new TextField("Nazwa");

        TextField tax_no = new TextField("tax_no");

        TextField post_code = new TextField("post_code");

        TextField city = new TextField("city");

        TextField street = new TextField("street");

        TextField first_name = new TextField("first_name");
        TextField country = new TextField("country");
        TextField email = new TextField("email");
        TextField phone = new TextField("phone");
        TextField www = new TextField("www");
        TextField fax = new TextField("fax");
        TextField created_at = new TextField("created_at");
        TextField updated_at = new TextField("updated_at");
        TextField street_no = new TextField("street_no");
        TextField kind = new TextField("kind");
        TextField bank = new TextField("bank");
        TextField bank_account = new TextField("bank_account");
        TextField shortcut = new TextField("shortcut");
        TextField last_name = new TextField("last_name");
        TextField mobile_phone = new TextField("mobile_phone");
        TextField register_number = new TextField("register_number");
        TextField tax_no_check = new TextField("mobile_phone");


        Button save = new Button("Save", buttonClickEvent -> {

            ClientDto clientDto = new ClientDto(
                    id.getValue(),
                    name.getValue(),
                    tax_no.getValue(),
                    post_code.getValue(),
                    city.getValue(),
                    street.getValue(),
                    first_name.getValue(),
                    country.getValue(),
                    email.getValue(),
                    phone.getValue(),
                    www.getValue(),
                    fax.getValue(),
                    new Date(),
                    new Date(),
                    street_no.getValue(),
                    kind.getValue(),
                    bank.getValue(),
                    bank_account.getValue(),
                    shortcut.getValue(),
                    last_name.getValue(),
                    mobile_phone.getValue(),
                    register_number.getValue(),
                    tax_no_check.getValue()
            );
            if(name.getValue().equals(""))
            {
                Notification notification = Notification.show(
                        "Pole 'nazwa' nie może być puste. Podaj nazwę klienta");
                notification.setPosition(Notification.Position.TOP_CENTER);
                add(notification);
            }
            else {
                clientController.addCLient(new AddClientDto(invoiceConfig.getToken(), clientDto));
                fetchClientAndAddToGrid();
                dialog.close();
            }
        });
        Button cancel = new Button("Cancel", buttonClickEvent -> {
            dialog.close();
        });

        MyCustomLayout upperLayout = new MyCustomLayout();

        upperLayout.addItemWithLabel("", name);
        upperLayout.addItemWithLabel("", tax_no);
        upperLayout.addItemWithLabel("", post_code);
        upperLayout.addItemWithLabel("", city);
        upperLayout.addItemWithLabel("", street);
        upperLayout.addItemWithLabel("", first_name);
        upperLayout.addItemWithLabel("", country);
        upperLayout.addItemWithLabel("", email);
        upperLayout.addItemWithLabel("", phone);
        upperLayout.addItemWithLabel("", www);
        upperLayout.addItemWithLabel("", fax);
        upperLayout.addItemWithLabel("", created_at);
        upperLayout.addItemWithLabel("", updated_at);
        upperLayout.addItemWithLabel("", street_no);
        upperLayout.addItemWithLabel("", kind);
        upperLayout.addItemWithLabel("", bank);
        upperLayout.addItemWithLabel("", bank_account);
        upperLayout.addItemWithLabel("", shortcut);
        upperLayout.addItemWithLabel("", last_name);
        upperLayout.addItemWithLabel("", mobile_phone);
        upperLayout.addItemWithLabel("", register_number);
        upperLayout.addItemWithLabel("", tax_no_check);
        upperLayout.addItemWithLabel("", cancel);
        upperLayout.addItemWithLabel("",save);

        dialog.add(upperLayout );

        return dialog;
    }

    public Dialog deleteDialog(int id) {

        Dialog dialog = new Dialog(new Text("Usuń klienta"));
        Text deleteTextConfirmation = new Text("Czy na pewno chcesz usunąć tego klienta?");
        Text emptyText = new Text("   ");
        Button delete = new Button("Usuń", buttonClickEvent -> {
            clientController.deleteClient((long)id);
            fetchClientAndAddToGrid();
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
