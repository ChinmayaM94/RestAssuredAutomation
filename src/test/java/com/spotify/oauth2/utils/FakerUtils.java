package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
	
	public static String getName() {
		Faker faker = new Faker();
		return "Playlist " + faker.name();
	}
	
	public static String getDescription() {
		Faker faker = new Faker();
		return "Playlist " + faker.regexify("[A-Za-z0-9]{15}");
	}

}
