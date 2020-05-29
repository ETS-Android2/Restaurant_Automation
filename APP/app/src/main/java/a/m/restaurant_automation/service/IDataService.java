package a.m.restaurant_automation.service;

import java.util.ArrayList;

import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.responseModel.TableResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IDataService {

    @GET("menu/{categoryId}")
    Call<ResponseModel<ArrayList<MenuItemResponse>>> getMenuItem(@Path(value = "categoryId")int categoryId);

    @GET("users?userType={userType}")
    Call<ResponseModel<ArrayList<RegisterResponseModel>>> getEmployees(@Path(value = "userType")int userType);

    @GET("table")
    Call<ResponseModel<ArrayList<TableResponseModel>>> getTable ();
}
