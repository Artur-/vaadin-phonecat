package com.example.vaadin_phonecat;

public class Phone {
	public String name;
	public String snippet;

	public Phone(String name, String snippet) {
		this.name = name;
		this.snippet = snippet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

}