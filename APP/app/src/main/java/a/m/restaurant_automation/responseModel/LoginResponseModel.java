package a.m.restaurant_automation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponseModel {
    @SerializedName("UserID")
    @Expose
    private String userId;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("UserType")
    @Expose
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

