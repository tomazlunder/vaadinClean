package com.packagename.vaadinclean.spring.tab;

import com.packagename.vaadinclean.spring.dialog.ChangePasswordDialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class SettingsTab extends Div{
    Button buttonChangePassword;
    Button buttonLogout;

    public SettingsTab() {
        //Change password button
        buttonChangePassword = new Button("Spremeni geslo");
        buttonChangePassword.addClickListener(event -> {
            Dialog dialog = new ChangePasswordDialog();
            dialog.open();
        });

        //Logout button
        buttonLogout = new Button("Odjava");
        buttonLogout.addClickListener(buttonClickEvent -> {
            System.out.println("ODJAVA CLICKED!");
            //UI.getCurrent().navigate("");
            VaadinSession.getCurrent().close();
            UI.getCurrent().navigate("login");
            //UI.getCurrent().getPage().reload();
        });

        //PAGE FORMATTING
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);

        verticalLayout.add(buttonLogout);
        verticalLayout.add(buttonChangePassword);

        Row row = new Row(new Label(),verticalLayout,new Label());
        add(row);
    }
}
