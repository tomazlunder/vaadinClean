package com.packagename.vaadinclean.spring.layout;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.communication.PushMode;

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_IS_AUTH;
import static com.packagename.vaadinclean.spring.view.LoginView.NAV;
import static java.lang.Boolean.FALSE;
import static java.util.Optional.ofNullable;

@Push(PushMode.MANUAL)
public class MainLayout extends VerticalLayout implements RouterLayout, BeforeEnterObserver {

    public MainLayout() {
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String path = event.getLocation().getPath();

        Boolean isAuthenticated = ofNullable((Boolean) VaadinSession.getCurrent().getAttribute(ATTRIBUTE_IS_AUTH))
                .orElse(FALSE);

        //second access - login view
        if (!isAuthenticated &&
                !path.startsWith(NAV)) {
            System.out.println("Not authenticated, not on login page");
            event.forwardTo(NAV, path);
        }
    }
}
