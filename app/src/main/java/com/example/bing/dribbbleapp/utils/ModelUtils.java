package com.example.bing.dribbbleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Bing on 2016/9/16.
 */
public class ModelUtils {

	private static Gson gson = new Gson();
	private static String PREF_NAME = "dribbble";

	public static void save(Context context, String key, Object object) {
		SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);

		String jsonString = gson.toJson(object);
		sharedPreferences.edit().putString(key, jsonString).apply();
	}

	public static <T> T read(Context context, String key, TypeToken<T> typeToken) {
		SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);

		try {
			return gson.fromJson(sharedPreferences.getString(key, ""), typeToken.getType());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T toObject(String json, TypeToken<T> typeToken) {
		return gson.fromJson(json, typeToken.getType());
	}

	public static <T> String toString(T object, TypeToken<T> typeToken) {
		return gson.toJson(object, typeToken.getType());
	}
}
