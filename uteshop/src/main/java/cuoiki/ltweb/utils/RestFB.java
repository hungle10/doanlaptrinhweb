package cuoiki.ltweb.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

import com.restfb.json.JsonObject;
import com.restfb.types.User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

public class RestFB {
	public static String getToken(final String code) throws ClientProtocolException, IOException {
		String link = String.format(Constants.FACEBOOK_LINK_GET_TOKEN, Constants.FACEBOOK_APP_ID,
				Constants.FACEBOOK_APP_SECRET, Constants.FACEBOOK_REDIRECT_URL, code);
		String response = Request.Get(link).execute().returnContent().asString();
		System.out.println(response);
		 // Phân tích chuỗi JSON
        JSONObject jsonObject = new JSONObject(response);

        // Lấy giá trị access_token
        String accessToken = jsonObject.getString("access_token");
		return accessToken;
	}

	public static User getUserInfo(String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Constants.FACEBOOK_APP_SECRET,
				Version.LATEST);
		return facebookClient.fetchObject("me", User.class);
	}
}
