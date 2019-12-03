package com.example.smartroom.model;

public class ResponseSignUpBody {
    private Boolean success;
    private String message;


    public ResponseSignUpBody(Boolean success, String message) {
        this.success=success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }

}
