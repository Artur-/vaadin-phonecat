package com.example.vaadin_phonecat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhoneDetails {
	private JSONObject data;

	public PhoneDetails(JSONObject data) {
		this.data = data;
	}

	public List<String> getPhoneImageUrls() {
		try {
			JSONArray arr = data.getJSONArray("images");
			return jsonArrayToStringList(arr);
		} catch (JSONException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}

	private List<String> jsonArrayToStringList(JSONArray arr)
			throws JSONException {
		List<String> images = new ArrayList<String>();
		for (int i = 0; i < arr.length(); i++) {
			images.add(arr.getString(i));
		}
		return images;
	}

	public String getData(String key) {
		try {
			return data.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAvailability() {
		try {
			JSONArray arr = data.getJSONArray("availability");
			return jsonArrayToStringList(arr);
		} catch (JSONException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}

	public boolean getBoolean(String key) {
		try {
			return getBoolean(data, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean getBoolean(JSONObject data, String key)
			throws JSONException {
		int dot = key.indexOf('.');
		if (dot > -1)
			return getBoolean(data.getJSONObject(key.substring(0, dot)),
					key.substring(dot+1));
		try {
			return data.getBoolean(key);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

}
