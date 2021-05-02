package com.emergencyguide.Entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一封装后台返回数据格式
 * */
public class Result<T> {

    //返回码
    private int code = 0;
    //返回消息
    private String msg;

    private int count = 0;
    private T model = null;
    private List<T> data = new ArrayList<T>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(this).toString();
    }

    public Result<T> success(String msg) {
        this.setCode(0);
        this.setMsg(msg);
        return this;
    }

    public Result<T> failed(String msg) {
        this.setCode(1);
        this.setMsg(msg);
        return this;
    }
}
