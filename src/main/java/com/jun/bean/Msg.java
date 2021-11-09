package com.jun.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HuangJun7
 * @date 2021-11-09 22:20
 */
@Data
public class Msg {
    //JSON数据通用的返回类
    private int code;//状态码 100成功 200失败
    private String msg;//提示信息
    //用户要返回给浏览器的数据
    private Map<String, Object> data = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    //链式编程
    public Msg add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }
}
