package com.example.vaadin_phonecat;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

public class PhoneLayout extends CssLayout {

	public PhoneLayout(Phone p) {
		setStyleName("phonelayout");
		setWidth("100%");
		Label label = new Label("<a href=\"#/phones/" + p.getId() + "\">"
				+ p.getName() + "</a>" + "<br/>" + p.getSnippet(),
				ContentMode.HTML);
		label.setWidth(null);
		Image image = new Image(null, new ThemeResource(p.getImage()));
		image.setStyleName("thumb");
		addComponent(image);
		addComponent(label);
	}
}
