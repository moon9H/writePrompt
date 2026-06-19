package com.ssafy.wp.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static ApiResponse<Void> ok(String message) {
        return new ApiResponse<>(message, null);
    }

    public static ApiResponse<Void> fail(String message) {
        return new ApiResponse<>(message, null);
    }
}