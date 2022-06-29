package com.example.demo.errors;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestError extends IllegalStateException {
    private int statusCode;
    private String statusMessage;
}
