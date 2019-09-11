package com.packagename.vaadinclean.spring.layout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.communication.PushMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_IS_AUTH;
import static com.packagename.vaadinclean.spring.view.LoginView.NAV;
import static java.lang.Boolean.FALSE;
import static java.util.Optional.ofNullable;

@Push(PushMode.MANUAL)
public class MainLayout extends VerticalLayout implements RouterLayout, BeforeEnterObserver {

    public MainLayout() {
        addClassName("mainLayout");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String path = event.getLocation().getPath();

        if(path.equals("login")) return;

        Boolean isAuthenticated = ofNullable((Boolean) VaadinSession.getCurrent().getAttribute(ATTRIBUTE_IS_AUTH)).orElse(FALSE);

        //if on login and already authenticated
        if (isAuthenticated && path.equals("login")){
            UI.getCurrent().navigate("app");
        }

        //if not on login and not authenticated
        if (!isAuthenticated && !path.startsWith(NAV)) {
            event.forwardTo("login");
        }
    }
}
