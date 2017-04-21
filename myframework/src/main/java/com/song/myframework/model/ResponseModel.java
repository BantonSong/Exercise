package com.song.myframework.model;

import com.google.gson.JsonObject;

/**
 * Created by songyawei on 2017/4/13.
 */
public class ResponseModel<T> {
    private String status;
    private String msg;
    private String errNum;
    private String errMsg;
    private JsonObject result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }

    public JsonObject getResult() {
        return result;
    }

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
