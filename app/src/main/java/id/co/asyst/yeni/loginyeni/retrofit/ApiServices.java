package id.co.asyst.yeni.loginyeni.retrofit;

import id.co.asyst.yeni.loginyeni.model.LoginModel;
import id.co.asyst.yeni.loginyeni.model.LoginRequest;
import id.co.asyst.yeni.loginyeni.retrofit.response.LoginResponse;
import id.co.asyst.yeni.loginyeni.retrofit.response.PgnResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("Login/getProfileInfo")
    Call<LoginResponse> getProfileInfo(@Body LoginRequest loginRequest);

    @GET("Task/getAllTask")
    Call<PgnResponse> getProfileInfo(@Body LoginModel loginModel);



}
