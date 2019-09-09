package com.packagename.vaadinclean.spring.view;

import com.packagename.vaadinclean.spring.layout.MainLayout;
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

import static com.packagename.vaadinclean.spring.view.LoginView.ATTRIBUTE_USERNAME;

@Route(value = "", layout = MainLayout.class)
//@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {
    public MainView(){
        setPadding(false);

        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);

        VaadinSession vaadinSession = VaadinSession.getCurrent();
        String username = (String) vaadinSession.getAttribute(ATTRIBUTE_USERNAME);

        Tab tab1 = new Tab("Tedensko");
        Tab tab2 = new Tab("Generalka");
        Tab tab3 = new Tab("Nastavitve");
        Tabs tabs = new Tabs(tab1, tab2, tab3);
        tabs.setSelectedIndex(0);
        tabs.setFlexGrowForEnclosedTabs(1);

        Div page1 = new Div();
        Div page2 = new Div();
        Div page3 = new SettingsTab();

        page1.setVisible(false);
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
        //page1.add(new Image("/images/vilko.png", "Alternative image text"));

        add(tabs, pages);
    }
}
