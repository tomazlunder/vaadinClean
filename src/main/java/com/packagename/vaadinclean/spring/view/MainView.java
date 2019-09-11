package com.packagename.vaadinclean.spring.view;

import com.packagename.vaadinclean.spring.layout.MainLayout;
import com.packagename.vaadinclean.spring.tab.DailyTab;
import com.packagename.vaadinclean.spring.tab.SettingsTab;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.VaadinSession;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_USERNAME;

@Route(value = "", layout = MainLayout.class)
//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {
    public MainView(){
        addClassName("mainView");

        add(new Label("MainView - you shouldn't be seeing this"));
    }
}
