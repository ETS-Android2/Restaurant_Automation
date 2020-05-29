package a.m.restaurant_automation.service;

import a.m.restaurant_automation.requestModel.AddMenuItemRequestModel;
import a.m.restaurant_automation.requestModel.AddTableRequestModel;
import a.m.restaurant_automation.requestModel.LoginRequestModel;
import a.m.restaurant_automation.requestModel.RegisterRequestModel;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.LoginResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import a.m.restaurant_automation.responseModel.TableResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserService {

    @POST ("auth/login")
    Call<ResponseModel<LoginResponseModel>> login (@Body LoginRequestModel loginRequestModel);

    @POST ("users/add")
    Call<ResponseModel<RegisterResponseModel>> register (@Body RegisterRequestModel registerRequestModel);

    @POST ("menu/addMenuItem")
    Call<ResponseModel<MenuItemResponse>> addMenuItem (@Body AddMenuItemRequestModel addMenuItemRequestModel);

    @POST ("addTable")
    Call<ResponseModel<TableResponseModel>> addTable (@Body AddTableRequestModel addTableRequestModel);

}
