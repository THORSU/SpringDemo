package org.yjg.entity;

import java.util.List;

public class Result<T> {
    private short status;
    private String msg;
    private String code;
    private List<T> datas;
    private T data;

    private Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result() {
        this.status = 0;
    }

    private Result(T data) {
        this.data = data;
        this.status = 0;
    }

    private Result(List<T> list) {
        this.datas = list;
        this.status = 0;
    }

    private Result(Exception e) {
        this.status = 1;
        this.msg = e.getMessage();
    }

    public static Result success() {
        return new Result();
    }

    public static <T> Result success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result success(List<T> datas) {
        return new Result<T>(datas);
    }

    public static Result error(Exception e) {
        return new Result(e);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg);
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
