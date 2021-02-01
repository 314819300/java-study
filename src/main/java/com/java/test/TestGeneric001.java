package com.java.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangning
 * @create 2020-12-12 16:21
 */
class Result<T> implements Serializable {

//    private static final long serialVersionUID = -6971381323819758511L;
    private Integer status = 200;
    private String message = "成功";
    private List<T> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

class Utils {
    public static  <T> Result<T> result(Integer status, String message, List<T> data) {
        Result<T> tResult = new Result<T>();
        tResult.setStatus(status);
        tResult.setMessage(message);
        tResult.setData(data);
        return tResult;

    }
}
class Boy {

}
public class TestGeneric001 {
    public static void main(String[] args) {
        Boy boy = new Boy();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(boy);
        Utils.result(200, "", objects);

    }
}
