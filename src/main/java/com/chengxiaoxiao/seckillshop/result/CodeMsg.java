package com.chengxiaoxiao.seckillshop.result;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
public class CodeMsg
{
    private int code;
    private String msg;

    //通用异常
    public static CodeMsg ERROR = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器端异常");


    public CodeMsg(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
