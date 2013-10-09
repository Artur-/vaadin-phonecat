package com.example.vaadin_phonecat;

import java.io.IOException;

import org.json.JSONException;

import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PhoneListView extends HorizontalLayout implements View {
	private BeanItemContainer<Phone> phoneContainer = null;
	private String orderProp = "age";

	private void build() {
		setSizeFull();
		setMargin(true);

		final VerticalLayout layout = new VerticalLayout();

		phoneContainer = new BeanItemContainer<Phone>(Phone.class);

		phoneContainer.addItemSetChangeListener(new ItemSetChangeListener() {
			@Override
			public void containerItemSetChange(ItemSetChangeEvent event) {
				populate(layout);
			}
		});
		try {
			phoneContainer.addAll(PhoneController.getPhones());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final VerticalLayout searchOrderLayout = new VerticalLayout();
		searchOrderLayout.setSizeUndefined();
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
			}
		});

		final ComboBox orderSelect = new ComboBox("Sort by:");
		orderSelect.setWidth("210px");
		orderSelect.setImmediate(true);
		orderSelect.setNullSelectionAllowed(false);
		orderSelect.setItemCaptionPropertyId("caption");
		orderSelect.addContainerProperty("caption", String.class, "");
		orderSelect.addItem("name").getItemProperty("caption")
				.setValue("Alphabetical");
		orderSelect.addItem("age").getItemProperty("caption")
				.setValue("Newest");
		orderSelect.setPropertyDataSource(new ObjectProperty(orderProp));
		orderSelect.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				phoneContainer.sort(new Object[] { orderSelect.getValue() },
						new boolean[] { true });

			}
		});

		searchOrderLayout.addComponents(searchField, orderSelect);
		addComponents(searchOrderLayout, layout);
		setExpandRatio(layout, 1);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (phoneContainer == null)
			build();
	}

	private void populate(VerticalLayout layout) {
		layout.removeAllComponents();
		for (Phone p : phoneContainer.getItemIds()) {
			layout.addComponent(new PhoneLayout(p));
		}

	}

}
