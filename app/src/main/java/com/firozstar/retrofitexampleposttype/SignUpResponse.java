package com.firozstar.retrofitexampleposttype;

public class SignUpResponse {

    private String success;

    private String message;
    private Integer userid;

    public SignUpResponse(String success, String message, Integer userid) {
        this.success = success;
        this.message = message;
        this.userid = userid;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
