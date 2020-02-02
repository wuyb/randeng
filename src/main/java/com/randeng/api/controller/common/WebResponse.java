package com.randeng.api.controller.common;

import java.io.Serializable;

public class WebResponse implements Serializable {

    private static final long serialVersionUID = 5633726320764850478L;

    private Boolean success;

    private Object data;

    private ErrorCode errorCode;

    public WebResponse() {
    }

    public WebResponse(Boolean success, Object data, ErrorCode code) {
        this.success = success;
        this.data = data;
        this.errorCode = code;
    }

    public static WebResponse error(String cause, ErrorCode code) {
        return new WebResponse(false, cause, code);
    }

    public static WebResponse success(Object data) {
        return new WebResponse(true, data, ErrorCode.SUCCESS);
    }

    public static WebResponse success() {
        return new WebResponse(true, null, ErrorCode.SUCCESS);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
