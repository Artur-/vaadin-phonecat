package com.example.vaadin_phonecat;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
		Label nexus = new Label(
				"Nexus S<br/>Fast just got faster with Nexus S.",
				ContentMode.HTML);
		Label motorola = new Label(
				"Motorola XOOM™ with Wi-Fi<br/>The Next, Next Generation tablet.",
				ContentMode.HTML);
		final VerticalLayout layout = new VerticalLayout(nexus, motorola);
		setContent(layout);
	}

}