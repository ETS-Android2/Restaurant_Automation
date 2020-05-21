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

    public void setUserType(int userType) {
        userPreferences.edit().putInt("UserType", userType).commit();
    }

    public void clearSession() {
        userPreferences.edit().putLong("token", 0).commit();
        userPreferences.edit().putString("userId", "").commit();
        userPreferences.edit().putString("email", "").commit();
        userPreferences.edit().putString("firstName", "").commit();
        userPreferences.edit().putString("lastName", "").commit();
        userPreferences.edit().putInt("userType", 0).commit();
    }

    public long getToken() {
        return userPreferences.getLong("token", 0);
    }

    public void setToken(long token) {
        userPreferences.edit().putLong("token", token).commit();
    }

    public void setEmail(String email) {
        userPreferences.edit().putString("email", email).commit();
    }

    public String getEmail() {
        return userPreferences.getString("email", "");
    }

    public void setUserId(String userId) {
        userPreferences.edit().putString("userId", userId).commit();
    }

    public String getUserId() {
        return userPreferences.getString("userId", "");
    }

    public void setfirstName(String firstname) {
        userPreferences.edit().putString("firstName", firstname).commit();
    }

    public String getfirstName() {
        return userPreferences.getString("firstName", "");
    }

    public void setName(String name) {
        String[] s = name.split(" ");
        setfirstName(s[0]);
        if (s.length > 1)
            setlastName(s[1]);

    }

    public void setlastName(String lastname) {
        userPreferences.edit().putString("lastName", lastname).commit();
    }

    public String getlastName() {
        return userPreferences.getString("lastName", "");
    }

    public int getUserType() {
        return userPreferences.getInt("userType", 0);
    }


}
