package a.m.restaurant_automation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginResponseModel {
    @SerializedName("Token")
    @Expose
    private String token;

    @SerializedName("UserID")
    @Expose
    private String userId;

    @SerializedName("ExpireDate")
    @Expose
    private String expireDate;

    @SerializedName("EmailId")
    @Expose
    private String emailId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}

