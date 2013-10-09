package com.example.vaadin_phonecat;

import org.json.JSONObject;

import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PhoneDetailsView extends GridLayout implements View {

	private Image image;
	private Label nameLabel;
	private Label descriptionLabel;
	private CssLayout phoneImagesContainer;
	private VerticalLayout specsList;

	public PhoneDetailsView() {
		super(2, 4);
		setWidth("100%");
		image = new Image();
		image.setStyleName("phone");

		nameLabel = new HeaderLabel("");
		descriptionLabel = new Label();

		phoneImagesContainer = new CssLayout();
		phoneImagesContainer.setStyleName("phone-thumbs");

		specsList = new VerticalLayout();
		specsList.setStyleName("specs");

		addComponent(image, 0, 0, 0, 1);
		addComponent(nameLabel, 1, 0);
		addComponent(descriptionLabel, 1, 1);
		addComponent(phoneImagesContainer, 0, 2, 1, 2);
		addComponent(specsList, 0, 3, 1, 3);
		setColumnExpandRatio(1, 1);
		setRowExpandRatio(1, 1);
	}

	private void setData(PhoneDetails p) {
		image.setSource(new ThemeResource(p.getPhoneImageUrls().get(0)));
		nameLabel.setValue(p.getData("name"));
		descriptionLabel.setValue(p.getData("description"));

		phoneImagesContainer.removeAllComponents();
		for (String url : p.getPhoneImageUrls()) {
			final Image img = new Image(null, new ThemeResource(url));
			img.addClickListener(new ClickListener() {

				@Override
				public void click(ClickEvent event) {
					image.setSource(img.getSource());
				}
			});
			phoneImagesContainer.addComponent(img);
		}

		specsList.addComponent(new HeaderLabel("Availability and Networks"));
		// specsList.addComponent(new Label("Availability"));
		for (String availability : p.getAvailability()) {
			specsList.addComponent(new Label(availability));

		}
		specsList.addComponent(new HeaderLabel("Additional Features"));
		specsList.addComponent(new Label(getCheckmark(p
				.getBoolean("connectivity.infrared")) + " Infrared"));
		specsList.addComponent(new Label(getCheckmark(p
				.getBoolean("connectivity.gps")) + " GPS"));
		specsList.addComponent(new Label(p.getData("additionalFeatures")));

	}

	private char getCheckmark(boolean b) {
		return b ? '\u2713' : '\u2718';
	}

	public static class HeaderLabel extends Label {

		public HeaderLabel(String string) {
			super(string);
			setWidth(null);
			setStyleName("header");
		}

	}

	@Override
	public void enter(ViewChangeEvent event) {
		String phoneId = event.getParameters();
		try {
			setData(PhoneController.getPhoneDetails(phoneId));
		} catch (Exception e) {
			e.printStackTrace();
			setData(new PhoneDetails(new JSONObject()));
		}

	}

}
