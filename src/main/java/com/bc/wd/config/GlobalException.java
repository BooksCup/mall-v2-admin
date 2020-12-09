package com.bc.wd.config;

import com.bc.wd.utils.CodeMsg;

/**
 * 全局异常设置
 *
 * @author whl
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
