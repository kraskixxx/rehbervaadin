package com.uniyaz;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class MyUI extends UI {

    PersonDbTransaction personDbTransaction;
    Person gettedPersonById;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();


        final TextField name = new TextField();
        name.setCaption("Ad");

        final TextField surname = new TextField();
        surname.setCaption("soyad");


        final TextField telNo = new TextField();
        telNo.setCaption("tel no");



        // BeanItemContainer<Person> container = new BeanItemContainer<Person>(Person.class);
        //Grid ....
        Grid grid = new Grid();


        layout.addComponent(grid);
        loadGridData(grid);


        //kisi ekle butonu
        Button kisiEkleButton = new Button("Kisi ekle");
        kisiEkleButton.addClickListener(e -> {

            String ad = name.getValue();
            String soyad = surname.getValue();
            String telNumarasi = telNo.getValue();

            Person person = new Person(ad, soyad, telNumarasi);

            personDbTransaction = new PersonDbTransaction();
            personDbTransaction.savePerson(person);

            layout.addComponent(new Label(name.getValue()
                    + ", basari ile olusturldu"));
        });


        //tüm kisileri göster butonu
        Button tumKisileriGosterButton = new Button("Tum kisileri goster");
        tumKisileriGosterButton.addClickListener(e -> {
            loadGridData(grid);

        });

        //güncelle butonu

        Button guncelleButton = new Button("Kisi guncelle");
        guncelleButton.addClickListener(e -> {
            String ad = name.getValue();
            String soyad = surname.getValue();
            String telNumarasi = telNo.getValue();

            Person person = (Person) grid.getSelectionModel().getSelectedRows().toArray()[0];
            Person uptatedPerson = new Person(person.getId(), ad, soyad, telNumarasi);

            personDbTransaction.updatePerson(uptatedPerson);

        });

        //sil butonu
        Button silButton = new Button("Kisi sil");
        silButton.addClickListener(e -> {
            Person person = (Person) grid.getSelectionModel().getSelectedRows().toArray()[0];

            Integer silId = person.getId();
            personDbTransaction.deletePerson(silId);
            //loadGridData(grid);
        });

        gridClickEventHandler(name, surname, telNo , grid, guncelleButton, silButton);


        layout.addComponents(name,surname,telNo, kisiEkleButton, tumKisileriGosterButton, guncelleButton, silButton);

        layout.setMargin(true);
        layout.setSpacing(true);


        setContent(layout);
    }

    private void gridClickEventHandler(TextField name, TextField surname, TextField telNo, Grid grid, Button guncelleButton, Button silButton) {
        grid.addItemClickListener(itemClickEvent -> {

            Person person = (Person) itemClickEvent.getItemId();
            name.setValue(person.getAd());
            surname.setValue(person.getSoyad());
            telNo.setValue(person.getTelNo());

        });
    }

    public void loadGridData(Grid grid){
        grid.getContainerDataSource().removeAllItems();

        personDbTransaction = new PersonDbTransaction();
        List<Person> personList = personDbTransaction.getAllPerson();

        IndexedContainer indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Integer.class, null);
        indexedContainer.addContainerProperty("ad", String.class, null);
        indexedContainer.addContainerProperty("soyad", String.class, null);
        indexedContainer.addContainerProperty("telNo", String.class, null);
        grid.setContainerDataSource(indexedContainer);

        for (Person person : personList) {

            Item item = indexedContainer.addItem(person);
            item.getItemProperty("id").setValue(person.getId());
            item.getItemProperty("ad").setValue(person.getAd());
            item.getItemProperty("soyad").setValue(person.getSoyad());
            item.getItemProperty("telNo").setValue(person.getTelNo());
        }


    }



    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
