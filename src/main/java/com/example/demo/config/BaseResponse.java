package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class BaseResponse<T> {
    private int code;
    private String status;
    private String message;
    private T data;

    public BaseResponse(int code, String status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, "SUCCESS", "Success", data);
    }

    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(code, "ERROR", message, null);
    }
}

