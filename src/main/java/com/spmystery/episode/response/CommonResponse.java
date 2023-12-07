package com.spmystery.episode.response;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private String code;

    private T data;

    private String errorMessage;

    public static CommonResponse<Object> build(Object data) {
        CommonResponse<Object> response = new CommonResponse<>();
        response.setCode("SUCCESS");
        response.setData(data);
        return response;
    }
}
