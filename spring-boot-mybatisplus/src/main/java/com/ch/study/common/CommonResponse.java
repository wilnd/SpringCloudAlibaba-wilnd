package com.ch.study.common;

import lombok.Data;

@Data
public class CommonResponse {

    private String code;

    private String message;

    private Object data;

}
