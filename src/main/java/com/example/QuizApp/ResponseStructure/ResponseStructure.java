package com.example.QuizApp.ResponseStructure;

public class ResponseStructure<T> {
    private int code;
    private String Msg;
    private T Data;

    public ResponseStructure() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "ResponseStructure{" +
                "code=" + code +
                ", Msg='" + Msg + '\'' +
                ", Data=" + Data +
                '}';
    }
}
