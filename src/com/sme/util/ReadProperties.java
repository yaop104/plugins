package com.sme.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ReadProperties {
	
	public static String getString(final String config, final String key) {
		final ResourceBundle resource = ResourceBundle.getBundle(config, Locale.getDefault());
		try {
			return resource.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
		String v = getString("config", "file.path");
		System.out.println(v);
	}

}
