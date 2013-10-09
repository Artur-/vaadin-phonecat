package com.example.vaadin_phonecat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneController {

	public static List<Phone> getPhones() {
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(new Phone("Nexus S", "Fast just got fast with Nexus S."));
		phones.add(new Phone("Motorola XOOM™ with Wi-Fi",
				"The Next, Next Generation tablet."));
		phones.add(new Phone("MOTOROLA XOOM™",
				"The Next, Next Generation tablet."));

		return phones;
	}
}
