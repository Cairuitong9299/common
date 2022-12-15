package com.bdp.idmapping.response;

/**
 * @Auther: CAI
 * @Date: 2022/11/2 - 11 - 02 - 21:19
 * @Description: com.bdp.idmapping.response
 * @version: 1.0
 */
public class Response<T> {

    private boolean success;

    private int status;

    private String msg;

    private T data;

    public Response() {
    }

    public Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response<T> fail(int status, String msg) {
        Response<T> response = new Response<>(status, msg);
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> success(T data, int status, String msg) {
        Response<T> response = new Response<>(status, msg);
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> success(int status, String msg) {
        Response<T> response = new Response<>(status, msg);
        response.setSuccess(true);
        return response;
    }
}
