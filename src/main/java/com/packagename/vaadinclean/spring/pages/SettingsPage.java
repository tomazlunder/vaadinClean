package com.packagename.vaadinclean.spring.pages;

import com.packagename.vaadinclean.spring.dialog.ChangePasswordDialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_USERNAME;

public class SettingsPage extends Div {
    Label statusLabel;

    public SettingsPage(){
        Button buttonChangePassword;
        Button buttonLogout;


        String username = (String) VaadinSession.getCurrent().getAttribute(ATTRIBUTE_USERNAME);

        VerticalLayout verticalLayout = new VerticalLayout();

        //Change password button
        buttonChangePassword = new Button("Change password");

        buttonChangePassword.addClickListener(event -> {
            Dialog dialog = new ChangePasswordDialog();
            dialog.open();
        });
        verticalLayout.add(buttonChangePassword);


        buttonLogout = new Button("Logout");
        buttonLogout.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("");
            VaadinSession.getCurrent().close();
        });
        verticalLayout.add(buttonLogout);

        add(verticalLayout);
    }
}
