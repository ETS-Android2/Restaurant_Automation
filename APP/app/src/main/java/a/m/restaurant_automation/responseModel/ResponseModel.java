package a.m.restaurant_automation.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel<T>{

    @SerializedName("data")
    @Expose
    private T Data;

    @SerializedName("error")
    @Expose
    private ArrayList<ErrorModel> error;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public ArrayList<ErrorModel> getError() {
        return error;
    }

    public void setError(ArrayList<ErrorModel> error) {
        this.error = error;
    }

}
