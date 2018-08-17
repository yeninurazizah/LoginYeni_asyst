package id.co.asyst.yeni.loginyeni.retrofit.response;

import java.util.ArrayList;

import id.co.asyst.yeni.loginyeni.model.PgnModel;

public class PgnResponse {

    String status;
    String message;
    ArrayList<PgnModel> data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<PgnModel> getData() {
        return data;
    }

    public void setData(ArrayList<PgnModel> data) {
        this.data = data;
    }
}
