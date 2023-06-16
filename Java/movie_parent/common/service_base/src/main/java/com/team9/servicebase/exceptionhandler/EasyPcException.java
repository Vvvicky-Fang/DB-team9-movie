package com.team9.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyPcException extends RuntimeException{
    private Integer code;
    private String msg;
}
