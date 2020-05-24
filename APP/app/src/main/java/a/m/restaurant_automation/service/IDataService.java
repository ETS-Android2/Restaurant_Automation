package a.m.restaurant_automation.service;

import java.util.ArrayList;

import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IDataService {

    @GET("menu/{categoryId}")
    Call<ResponseModel<ArrayList<MenuItemResponse>>> getMenuItem(@Path(value = "categoryId")int categoryId);
}
