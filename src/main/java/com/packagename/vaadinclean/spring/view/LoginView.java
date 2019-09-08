package com.packagename.vaadinclean.spring.view;

import com.packagename.vaadinclean.spring.AccessControl;
import com.packagename.vaadinclean.spring.layout.MainLayout;
import com.packagename.vaadinclean.spring.SimpleAccessControl;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import static com.packagename.vaadinclean.spring.view.LoginView.*;

//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@Route(value = NAV, layout = MainLayout.class)
public class LoginView extends VerticalLayout implements HasUrlParameter<String> {
    public static final String NAV = "login";
    public static final String ATTRIBUTE_USERNAME = "username";
    public static final String ATTRIBUTE_IS_AUTH = "auth";

    private String parameter;

    public LoginView(){
        LoginForm component = new LoginForm();
        add(component);

        AccessControl accessControl = new SimpleAccessControl();

        component.addLoginListener(e -> {
            boolean isAuthenticated = accessControl.checkUsernamePassword(e.getUsername(), e.getPassword());
            if (isAuthenticated) {
                VaadinSession vaadinSession = VaadinSession.getCurrent();
                vaadinSession.setAttribute(ATTRIBUTE_USERNAME, e.getUsername());
                vaadinSession.setAttribute(ATTRIBUTE_IS_AUTH, true);

                UI.getCurrent().navigate(parameter);

            } else {
                component.setError(true);
            }
        });

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        this.parameter = s;
    }
}
