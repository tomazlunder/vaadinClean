package com.packagename.vaadinclean.spring.dialog;

import com.packagename.vaadinclean.spring.AccessControl;
import com.packagename.vaadinclean.spring.SimpleAccessControl;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_USERNAME;

public class ChangePasswordDialog extends Dialog{
    Label statusLabel;

    public ChangePasswordDialog(){
        String username = (String) VaadinSession.getCurrent().getAttribute(ATTRIBUTE_USERNAME);

        add(new Label("Change password"));

        TextField textFieldOldPassword = new TextField("Old password");
        TextField textFieldNewPassword = new TextField("New password");

        statusLabel = new Label("");

        statusLabel.setText("");

        Button buttonChange = new Button("OK");
        Button buttonCancel = new Button("Cancel");
        buttonCancel.addClickListener(buttonClickEvent -> close());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(buttonChange, buttonCancel);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(textFieldOldPassword);
        verticalLayout.add(textFieldNewPassword);
        verticalLayout.add(statusLabel);
        verticalLayout.add(horizontalLayout);

        buttonChange.addClickListener(buttonClickEvent -> {
            statusLabel.setText("");
            AccessControl accessControl = new SimpleAccessControl();

            if(!accessControl.checkUsernamePassword(username,textFieldOldPassword.getValue())){
                statusLabel.setText("Old password incorrect.");
                return;
            }

            if(!accessControl.setPassword(username, textFieldNewPassword.getValue())){
                statusLabel.setText("Something went wrong.");
                return;
            }

            statusLabel.setText("Password changed. Logging out...");
            UI.getCurrent().push();

            int a = 3;

            try {
                Thread.sleep(2000);
                closeAndLogout();
            } catch (InterruptedException e) {
                e.printStackTrace();
                closeAndLogout();
            }
        });

        add(verticalLayout);
    }

    private void closeAndLogout(){
        close();
        UI.getCurrent().navigate("");
        VaadinSession.getCurrent().close();
    }
}
