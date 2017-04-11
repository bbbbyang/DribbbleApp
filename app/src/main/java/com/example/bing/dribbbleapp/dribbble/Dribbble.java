package com.example.bing.dribbbleapp.dribbble;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.CookieManager;

import com.example.bing.dribbbleapp.model.Shot;
import com.example.bing.dribbbleapp.model.User;
import com.example.bing.dribbbleapp.utils.ModelUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Bing on 2016/9/18.
 */
public class Dribbble {

	private static final String API_URL = "https://api.dribbble.com/v1/";

	private static final String SP_AUTH = "auth";
	private static final String KEY_ACCESS_TOKEN = "access_token";
	private static final String USER_END_POINT = API_URL + "user";
	private static final String SHOTS_END_POINT = API_URL + "shots";
	private static final String KEY_USER = "user";

	private static final TypeToken<User> USER_TYPE_TOKEN = new TypeToken<User>(){};
	private static final TypeToken<List<Shot>> SHOT_TYPE_TOKEN = new TypeToken<List<Shot>>(){};

	private static String accessToken;
	private static User user;

	private static OkHttpClient client = new OkHttpClient();

	private static Request.Builder authRequestBuilder(String url) {
		Request.Builder requestBuilder = new Request.Builder().addHeader("Authorization", "Bearer " + accessToken).url(url);
		return requestBuilder;
	}

	private static Response makeRequest(Request request) throws IOException {
		Response response = client.newCall(request).execute();
		return response;
	}

	private static Response makeGetRequest(String url) throws IOException {
		Request request = authRequestBuilder(url).build();
		return makeRequest(request);
	}

	private static <T> T parseResponse(Response response, TypeToken<T> typeToken) throws IOException, JsonSyntaxException{
		String responseString = response.body().string();
		return ModelUtils.toObject(responseString, typeToken);
	}

	private static User getUser() throws IOException ,JsonSyntaxException {
		return parseResponse(makeGetRequest(USER_END_POINT), USER_TYPE_TOKEN);
	}

	public static boolean isLoggedIn() {
		return  accessToken != null;
	}

	public static void init(Context context) {
		accessToken = loadAccessToken(context);
		if(accessToken != null) {
			user = loadUser(context);
		}
	}

	public static void login(Context context, String accessToken) throws IOException, JsonSyntaxException {
		Dribbble.accessToken = accessToken;
		storeAccessToken(context, accessToken);

		Dribbble.user = getUser();
		storeUser(context, user);
	}

	public static void logout(Context context) {
		storeAccessToken(context, null);
		storeUser(context, null);

		accessToken = null;
		user = null;

		CookieManager.getInstance().removeAllCookies(null);
		CookieManager.getInstance().flush();
	}

	public static User getCurrentUser() {
		return user;
	}

	public static void storeAccessToken (Context context, String token) {
		SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SP_AUTH, Context.MODE_PRIVATE);
		sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, token).apply();
	}

	public static String loadAccessToken(Context context) {
		SharedPreferences sharedPreferences =  context.getApplicationContext().getSharedPreferences(SP_AUTH, Context.MODE_PRIVATE);
		return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
	}

	public static void storeUser(Context context, User user) {
		ModelUtils.save(context, KEY_USER, user);
	}

	public static User loadUser(Context context) {
		return ModelUtils.read(context, KEY_USER, USER_TYPE_TOKEN);
	}

	public static List<Shot> getShots(int page) throws IOException, JsonSyntaxException {
		String url = SHOTS_END_POINT + "?page=" + page;
		return parseResponse(makeGetRequest(url), SHOT_TYPE_TOKEN);
	}

}
