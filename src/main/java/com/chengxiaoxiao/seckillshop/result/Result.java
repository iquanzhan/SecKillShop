package com.chengxiaoxiao.seckillshop.result;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;


    private Result(T data) {
        this.data = data;
    }


    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //成功时的回调
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<>(cm);
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
