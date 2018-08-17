package id.co.asyst.yeni.loginyeni.retrofit.request;

import id.co.asyst.yeni.loginyeni.model.LoginModel;

public class LoginRequest {

    String method;
    LoginModel param;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LoginModel getParam() {
        return param;
    }

    public void setParam(LoginModel param) {
        this.param = param;
    }
}
