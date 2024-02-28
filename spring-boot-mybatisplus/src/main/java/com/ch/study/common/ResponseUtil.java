package com.ch.study.common;

import lombok.Data;

@Data
public class ResponseUtil {

    private static final String SUCCESS_CODE = "0000";

    private static final String FAIL_CODE = "9999";

    private static final String SUCCESS_MESSAGE = "success";

    public static CommonResponse success(Object data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(SUCCESS_CODE);
        commonResponse.setMessage(SUCCESS_MESSAGE);
        commonResponse.setData(data);
        return commonResponse;
    }

    public static CommonResponse success() {
        return success(null);
    }

    public static CommonResponse fail(String code, String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        return commonResponse;
    }

    public static CommonResponse fail(String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(FAIL_CODE);
        commonResponse.setMessage(message);
        return commonResponse;
    }

}
