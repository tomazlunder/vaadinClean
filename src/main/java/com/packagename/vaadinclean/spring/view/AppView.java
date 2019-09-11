package com.packagename.vaadinclean.spring.view;

import com.packagename.vaadinclean.spring.layout.MainLayout;
import com.packagename.vaadinclean.spring.tab.DailyTab;
import com.packagename.vaadinclean.spring.tab.SettingsTab;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
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

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_IS_AUTH;
import static java.lang.Boolean.FALSE;
import static java.util.Optional.ofNullable;

@Route(value = "app", layout = MainLayout.class)
public class AppView extends VerticalLayout {

    public AppView(){
        addClassName("appView");
        setPadding(false);

        //If the user is not yet authenticated we don't load the page!
        Boolean isAuthenticated = ofNullable((Boolean) VaadinSession.getCurrent().getAttribute(ATTRIBUTE_IS_AUTH)).orElse(FALSE);
        if(!isAuthenticated){
            //Don't do shit
            return;
        }

        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);

        Tab tab1 = new Tab("Dnevno");
        Tab tab2 = new Tab("Generalka");
        Tab tab3 = new Tab("Nastavitve");
        Tabs tabs = new Tabs(tab1, tab2, tab3);
        tabs.setClassName("tabs");

        tabs.setSelectedIndex(0);
        tabs.setFlexGrowForEnclosedTabs(1);

        Div page1 = new DailyTab();
        Div page2 = new Div();
        Div page3 = new SettingsTab();


        page1.setVisible(true);
        page2.setVisible(false);
        page3.setVisible(false);

        Div pages = new Div(page1, page2, page3);

        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());


        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);

        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            if (selectedPage.equals(page2)) {
                //page2.update();
            }
            pagesShown.add(selectedPage);
        });

        add(tabs, pages);
    }
}
