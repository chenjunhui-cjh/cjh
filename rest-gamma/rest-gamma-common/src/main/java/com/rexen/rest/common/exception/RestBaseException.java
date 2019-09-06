package com.rexen.rest.common.exception;

/**
 * REST异常基类
 */
public class RestBaseException extends Exception {

    /**
     * 异常代码
     */
    private String code = "none";

    /**
     * 构造函数
     *
     * @param message 异常信息
     */
    public RestBaseException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param message 异常信息
     * @param cause
     */
    public RestBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造函数
     *
     * @param cause
     */
    public RestBaseException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数
     *
     * @param message 异常信息
     * @param code    异常代码
     */
    public RestBaseException(String message, String code) {
        this(message);
        this.code = code;
    }

    /**
     * 获取异常代码
     *
     * @return 异常代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置异常代码
     *
     * @param code 异常代码
     */
    public void setCode(String code) {
        this.code = code;
    }
}
