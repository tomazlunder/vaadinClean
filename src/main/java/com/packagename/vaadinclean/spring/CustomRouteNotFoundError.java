package com.packagename.vaadinclean.spring;

import com.packagename.vaadinclean.spring.layout.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.*;

import javax.servlet.http.HttpServletResponse;

@ParentLayout(MainLayout.class)
public class CustomRouteNotFoundError extends RouteNotFoundError {

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        //UI.getCurrent().navigate("");
        event.rerouteTo("app");
        return HttpServletResponse.SC_ACCEPTED;
    }
}
