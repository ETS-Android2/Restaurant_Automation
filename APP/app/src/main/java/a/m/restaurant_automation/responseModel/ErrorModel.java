package a.m.restaurant_automation.responseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ErrorModel {

    public ArrayList<ErrorModel> getError() {return error;}

    public void setError(ArrayList<ErrorModel> error) {
        this.error = error;
    }

    @SerializedName("error")
    @Expose
    private ArrayList<ErrorModel> error;

}
