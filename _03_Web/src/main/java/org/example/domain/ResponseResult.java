package org.example.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/* 将所有返回的Json结果统一使用该类进行封装并发往前端 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    // 状态码
    private Integer code;
    // 提示信息
    private String msg;
    // 查询到的结果信息
    private T data;

    public ResponseResult(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public ResponseResult(Integer code, T data){
        this.code = code;
        this.data = data;
    }
    public ResponseResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
