package com.rexen.rest.common.exception;

/**
 * 结果为空异常
 */
public class RestNullResultException extends RestBaseException {

    /**
     * 构造函数
     *
     * @param message 异常信息
     * @param code    异常代码
     */
    public RestNullResultException(String message, String code) {
        super(message, code);
    }

    /**
     * 构造函数
     *
     * @param message 异常信息
     */
    public RestNullResultException(String message) {
        super(message);
    }
}
