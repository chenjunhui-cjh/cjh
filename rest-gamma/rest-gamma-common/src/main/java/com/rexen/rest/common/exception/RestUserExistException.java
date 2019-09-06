package com.rexen.rest.common.exception;

/**
 * 用户已存在异常
 *
 * @author GavinHacker
 */
public class RestUserExistException extends RestBaseException {

    /**
     * 构造函数
     *
     * @param message 异常信息
     * @param code    异常代码
     */
    public RestUserExistException(String message, String code) {
        super(message, code);
    }

    /**
     * 构造函数
     *
     * @param message 异常信息
     */
    public RestUserExistException(String message) {
        super(message);
    }
}
