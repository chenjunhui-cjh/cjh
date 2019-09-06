package com.rexen.rest.common.exception;

/**
 * 服务未找到异常
 *
 * @author 刘晏麟
 */
public class RestServiceNotFoundException extends RestBaseException {

    /**
     * 构造函数
     *
     * @param message 异常信息
     */
    public RestServiceNotFoundException(String message) {
        super(message);
    }
}
