package id.co.asyst.yeni.loginyeni.retrofit;

import id.co.asyst.yeni.loginyeni.retrofit.request.LoginRequest;
import id.co.asyst.yeni.loginyeni.retrofit.request.PgnRequest;
import id.co.asyst.yeni.loginyeni.retrofit.response.LoginResponse;
import id.co.asyst.yeni.loginyeni.retrofit.response.PgnResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("Login/getProfileInfo")
    Call<LoginResponse> getProfileInfo(@Body LoginRequest loginRequest);

    @POST("Task/getAllTask")
    Call<PgnResponse> getAllTask(@Body PgnRequest pgnRequest);


}
