package com.example.vaadin_phonecat;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;

public class Vaadin_phonecatUI extends UI {

	private BeanItemContainer<Phone> phoneContainer;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadin_phonecatUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final HorizontalLayout mainLayout = new HorizontalLayout();
		setContent(mainLayout);

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		phoneContainer = new BeanItemContainer<Phone>(
				Phone.class, PhoneController.getPhones());

		populate(layout);

		TextField searchField = new TextField("Search:");
		searchField.setTextChangeEventMode(TextChangeEventMode.EAGER);
		searchField.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				SimpleStringFilter nameMatch = new SimpleStringFilter("name",
						event.getText(), true, false);
				SimpleStringFilter snippetMatch = new SimpleStringFilter(
						"snippet", event.getText(), true, false);
				phoneContainer.removeAllContainerFilters();
				phoneContainer.addContainerFilter(new Or(nameMatch,
						snippetMatch));
				populate(layout);

			}
		});

		mainLayout.addComponents(searchField, layout);
	}

	private void populate(VerticalLayout layout) {
		layout.removeAllComponents();
		for (Phone p : phoneContainer.getItemIds()) {
			Label label = new Label(p.name + "<br/>" + p.snippet,
					ContentMode.HTML);
			layout.addComponent(label);
		}

	}
}