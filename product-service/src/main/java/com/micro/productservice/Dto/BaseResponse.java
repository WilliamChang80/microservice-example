package com.micro.productservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class BaseResponse {
    private int code;
    private String message;
    private Object data;
}
