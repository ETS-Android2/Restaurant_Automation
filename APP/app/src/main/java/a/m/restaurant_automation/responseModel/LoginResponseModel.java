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

    @SerializedName("Token")
    @Expose
    private String token;



    @SerializedName("UserTypeId")
    @Expose
    private Integer userTypeId;
    @SerializedName("TokenCreatedDate")
    @Expose
    private String tokenCreatedDate;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getTokenCreatedDate() {
        return tokenCreatedDate;
    }

    public void setTokenCreatedDate(String tokenCreatedDate) {
        this.tokenCreatedDate = tokenCreatedDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    @SerializedName("ExpireDate")
    @Expose
    private String expireDate;



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

