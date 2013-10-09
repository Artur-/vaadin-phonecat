package com.example.vaadin_phonecat;

import java.util.ArrayList;
import java.util.List;

public class PhoneController {

	public static List<Phone> getPhones() {
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(new Phone("Nexus S", "Fast just got fast with Nexus S.",1));
		phones.add(new Phone("Motorola XOOM™ with Wi-Fi",
				"The Next, Next Generation tablet.",2));
		phones.add(new Phone("MOTOROLA XOOM™",
				"The Next, Next Generation tablet.",3));

		return phones;
	}
}
