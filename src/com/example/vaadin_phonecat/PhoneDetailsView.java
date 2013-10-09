package com.example.vaadin_phonecat;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PhoneDetailsView extends VerticalLayout implements View {

	@Override
	public void enter(ViewChangeEvent event) {
		String phoneId = event.getParameters();
		removeAllComponents();
		addComponent(new Label("TBD: detail view for " + phoneId));

	}

}
