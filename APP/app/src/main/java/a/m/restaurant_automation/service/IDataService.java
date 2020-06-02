package a.m.restaurant_automation.service;
import java.util.ArrayList;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.responseModel.TableReservationStatusForCustomerModel;
import a.m.restaurant_automation.responseModel.TableResponseModel;

import a.m.restaurant_automation.responseModel.UsersResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDataService {

    @GET("menu/{categoryId}")
    Call<ResponseModel<ArrayList<MenuItemResponse>>> getMenuItem(@Path(value = "categoryId")int categoryId);


    @GET("users")
    Call<ResponseModel<ArrayList<UsersResponseModel>>> getEmployees(/*@Query(value = "userType")int[] userType*/);
    @GET("table")
    Call<ResponseModel<ArrayList<TableResponseModel>>> getTable ();

    @GET("table/isTableReserved/{clientId}")
    Call<TableReservationStatusForCustomerModel> getReservationStatus(@Path(value = "clientId")int clientId);
}
