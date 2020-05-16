package a.m.restaurant_automation.service;

import a.m.restaurant_automation.requestModel.LoginRequestModel;
import a.m.restaurant_automation.requestModel.RegisterRequestModel;
import a.m.restaurant_automation.responseModel.RegisterResponseModel;
import a.m.restaurant_automation.responseModel.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserService {

    @POST ("auth/login")
    Call<ResponseModel<String>> login (@Body LoginRequestModel loginRequestModel);

    @POST ("users/add")
    Call<ResponseModel<RegisterResponseModel>> register (@Body RegisterRequestModel registerRequestModel);
}
