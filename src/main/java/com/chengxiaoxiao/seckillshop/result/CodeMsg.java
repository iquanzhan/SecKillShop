package com.chengxiaoxiao.seckillshop.result;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
public class CodeMsg {
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg ERROR = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");


    //登录模块
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500200, "手机号码不能为空");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500201, "密码不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500202, "手机号码格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500202, "手机号码不存在");
    public static CodeMsg MOBILE_PASSWORD_ERROR = new CodeMsg(500202, "用户名或者密码错误");


    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    /**
     * 添加带参数的异常处理
     *
     * @param obj
     * @return
     */
    public CodeMsg fillArgs(Object... obj) {
        int code = this.code;
        String message = String.format(msg, obj);
        return new CodeMsg(code, message);
    }
}
