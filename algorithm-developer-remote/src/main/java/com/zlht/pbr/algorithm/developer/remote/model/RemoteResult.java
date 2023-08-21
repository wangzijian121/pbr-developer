package com.zlht.pbr.algorithm.developer.remote.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteResult<T> {

    private Integer code;

    /**
     * message
     */
    private String msg;

    /**
     * data
     */
    private T data;

}