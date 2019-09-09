package com.packagename.vaadinclean.spring.view;

import com.packagename.vaadinclean.spring.AccessControl;
import com.packagename.vaadinclean.spring.layout.MainLayout;
import com.packagename.vaadinclean.spring.SimpleAccessControl;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import static com.packagename.vaadinclean.spring.view.LoginView.*;

//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@CssImport("./styles/testStyle.css")

@Route(value = NAV, layout = MainLayout.class)
public class LoginView extends VerticalLayout implements HasUrlParameter<String> {
    public static final String NAV = "login";
    public static final String ATTRIBUTE_USERNAME = "username";
    public static final String ATTRIBUTE_IS_AUTH = "auth";

    private String parameter;

    public LoginView(){
        addClassName("loginView");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        LoginForm component = new LoginForm(createCustomI18n());
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

        component.addForgotPasswordListener(forgotPasswordEvent -> {
            UI.getCurrent().getPage().open("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        });

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        this.parameter = s;
    }

    private LoginI18n createCustomI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("HeaderTitle");
        i18n.getHeader().setDescription("HeaderDescription");
        i18n.getForm().setTitle("Vaadin Clean");
        i18n.getForm().setUsername("Uporabniško ime");
        i18n.getForm().setSubmit("PRIJAVA");
        i18n.getForm().setPassword("Geslo");
        i18n.getForm().setForgotPassword("Pozabljeno geslo");
        i18n.getErrorMessage().setTitle("Napaka!");
        i18n.getErrorMessage()
                .setMessage("Napačno uporabniško ime ali geslo.");
        //i18n.setAdditionalInformation(
        //        "additional info");

        return i18n;
    }
}
