package com.chengxiaoxiao.seckillshop.exception;

import com.chengxiaoxiao.seckillshop.result.CodeMsg;

public class GlobleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobleException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.cm = codeMsg;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
