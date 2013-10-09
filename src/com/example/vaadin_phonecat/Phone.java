package com.example.vaadin_phonecat;

public class Phone {
	private String name;
	private String snippet;
	private int age;

	public Phone(String name, String snippet, int age) {
		this.name = name;
		this.snippet = snippet;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}