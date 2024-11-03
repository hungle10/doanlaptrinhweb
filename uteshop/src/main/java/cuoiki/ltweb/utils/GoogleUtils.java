package cuoiki.ltweb.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.restfb.json.JsonObject;

public class GoogleUtils {
	public static String getToken(final String code) throws ClientProtocolException, IOException {
		String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
						.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		JSONObject jsonObject = new JSONObject(response);
		// Lấy giá trị access_token
		String accessToken = jsonObject.getString("access_token");
		return accessToken;
	}

	public static GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
	    System.out.println(googlePojo);
	    return googlePojo;
	}
}
