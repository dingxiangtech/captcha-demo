package com.dingxianginc.captcha.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -6629012691534400264L;
    private Boolean success;
    private T data;
    private String msg;
    private String errorCode;

    public ApiResult(Boolean success, T data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public static <T> ApiResult getSuccess(T data) {
        return new ApiResult(true, data, null, null);
    }

    public static ApiResult getFailed(String msg) {
        return new ApiResult(false, null, msg, null);
    }

    public static ApiResult getFailed(String msg, String errorCode) {
        return new ApiResult(false, null, msg, errorCode);
    }

}
