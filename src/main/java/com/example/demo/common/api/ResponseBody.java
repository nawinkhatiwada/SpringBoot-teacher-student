package com.example.demo.common.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBody{
    private int status;
    private String message;
    private Object data;
}
