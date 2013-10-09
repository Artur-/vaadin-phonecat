package com.example.vaadin_phonecat;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Vaadin_phonecatUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadin_phonecatUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
		

		BeanItemContainer<Phone> phoneContainer = new BeanItemContainer<Phone>(
				Phone.class, PhoneController.getPhones());

		for (Phone p : phoneContainer.getItemIds()) {
			Label label = new Label(p.name + "<br/>" + p.snippet, ContentMode.HTML);
			layout.addComponent(label);
		}
	}
}