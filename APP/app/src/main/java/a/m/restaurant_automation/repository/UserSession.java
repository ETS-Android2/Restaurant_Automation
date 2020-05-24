package a.m.restaurant_automation.repository;

import android.content.SharedPreferences;

public class UserSession {

    private UserSession() {
    }

    private static SharedPreferences userPreferences;
    private static UserSession userSession;

    public static UserSession getInstance(SharedPreferences preferences) {

        if (userPreferences == null) {
            userPreferences = preferences;
        }

        if (userSession == null)
            userSession = new UserSession();
        return userSession;
    }

    public static UserSession getInstance() {
        if (userSession == null)
            userSession = new UserSession();
        return userSession;
    }

    public void clearSession() {
        userPreferences.edit().putString("token", "").commit();
        userPreferences.edit().putString("userId", "").commit();
        userPreferences.edit().putString("email", "").commit();
        userPreferences.edit().putString("name", "").commit();
        userPreferences.edit().putString("tokenCreatedDate", "").commit();
        userPreferences.edit().putString("expireDate", "").commit();
        userPreferences.edit().putInt("userTypeId", 0).commit();
    }

    public String getToken() {
        return userPreferences.getString("token", "");
    }

    public void setToken(String token) {
        userPreferences.edit().putString("token", token).apply();
    }

    public void setEmail(String email) {
        userPreferences.edit().putString("email", email).apply();
    }

    public String getEmail() {
        return userPreferences.getString("email", "");
    }

    public void setUserId(String userId) {
        userPreferences.edit().putString("userId", userId).apply();
    }

    public String getUserId() {
        return userPreferences.getString("userId", "");
    }

    public void setName(String name) {
        userPreferences.edit().putString("name",name).apply();
    }
    public String getName(){return userPreferences.getString("name","");}

    public void setUserTypeId(int userTypeId) {
        userPreferences.edit().putInt("userTypeId",userTypeId).apply();
    }
    public int getUserTypeId(){return userPreferences.getInt("userTypeId",0);}


    public void setTokenCreatedDate(String tokenCreatedDate) {
        userPreferences.edit().putString("tokenCreatedDate",tokenCreatedDate).commit();
    }
    public String getTokenCreatedDate(){return userPreferences.getString("tokenCreatedDate","");}

    public void setExpireDate(String expireDate) {
        userPreferences.edit().putString("expireDate",expireDate).commit();
    }
    public String getExpireDate(){return userPreferences.getString("expireDate","");}






}
