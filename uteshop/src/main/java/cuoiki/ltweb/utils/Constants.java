package cuoiki.ltweb.utils;

public class Constants {
	  public static String FACEBOOK_APP_ID = "2211151999254702";
	  public static String FACEBOOK_APP_SECRET = "379c6136b28d68058b2bc23f22700625";
	  public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/uteshop/login-facebook";
	  public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
	  
	  
	  public static String GOOGLE_CLIENT_ID = "313316268160-nj8kfng0k5fh4b3aib5ev041a06agf0q.apps.googleusercontent.com";
	  public static String GOOGLE_CLIENT_SECRET = "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";
	  public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/uteshop/login-google";
	  public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	  public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	  public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
