package com.packagename.vaadinclean.spring.tab;

import com.packagename.vaadinclean.spring.Application;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_USERNAME;

//@StyleSheet("frontend/styles/testStyle.css")
//@CssImport("./styles/testStyle.css")
//@CssImport("./styles/testStyle.css")

public class DailyTab extends Div {
    private ArrayList<Checkbox> opravila;
    Button buttonPotrdi;

    boolean isUserDezuren = false;
    boolean isDayDone = false;

    public DailyTab(){
        opravila = new ArrayList<>();

        addClassName("dailyTab");
        //Is user dezuren?
        String dezurniUsername = Application.databaseHandler.getDezurni();
        String username = (String) VaadinSession.getCurrent().getAttribute(ATTRIBUTE_USERNAME);
        if(dezurniUsername.equals(username)) isUserDezuren = true;

        //So opravila ze koncana?
        isDayDone = Application.databaseHandler.isDayDone();

        //Layout
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(new Label("Dezurni cimer: " + dezurniUsername.toUpperCase() + ". (samo dezurni lahko potrdi opravljenost opravil)"));
        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);

        if(isDayDone){
            Label dayDone = new Label("STATUS: Dnevna opravila so opravljena! :)");
            dayDone.addClassName("dayDoneLabel");
            verticalLayout.add(dayDone);

            if(isUserDezuren) {
                Button buttonResetDayDone = new Button("Resetiraj opravljenost.");
                buttonResetDayDone.addClickListener(buttonClickEvent -> {
                    Application.databaseHandler.resetCurrentDayDone();

                    UI.getCurrent().getPage().reload();
                });
                verticalLayout.add(buttonResetDayDone);
            }

            add(verticalLayout);

            return;
        }

        Label dayNotDone = new Label("STATUS: Dnevna opravila niso opravljena.");
        dayNotDone.addClassName("dayNotDoneLabel");
        verticalLayout.add(dayNotDone);

        fetchAndCreateOpravila();
        for(Checkbox cb : opravila) verticalLayout.add(cb);

        buttonPotrdi = new Button("Potrdi opravljenost opravil");
        buttonPotrdi.setEnabled(false);

        buttonPotrdi.addClickListener(buttonClickEvent -> {
           Application.databaseHandler.setCurrentDayDone();

            UI.getCurrent().getPage().reload();
        });

        verticalLayout.add(buttonPotrdi);

        add(verticalLayout);

    }

    public void fetchAndCreateOpravila(){
        ArrayList<ArrayList<String>> aas = Application.databaseHandler.getOpravila();

        for(ArrayList<String> pai : aas){
            opravila.add(createCheck(pai.get(0),pai.get(1)));
        }
    }

    public Checkbox createCheck(String name, String desc){
        Checkbox checkbox = new Checkbox();
        checkbox.setLabelAsHtml("<b>"+name+"</b> "+desc);

        if(!isUserDezuren || isDayDone){
            checkbox.setEnabled(false);
        }

        checkbox.addValueChangeListener(checkboxBooleanComponentValueChangeEvent -> {
           if(isAllCheckboxesTrue()){
               buttonPotrdi.setEnabled(true);
           }
           else{
               buttonPotrdi.setEnabled(false);
           }
        });

        return checkbox;
    }

    public boolean isAllCheckboxesTrue(){
        for(Checkbox cb : opravila){
            if(!cb.getValue()) return false;
        }
        return true;
    }
}
