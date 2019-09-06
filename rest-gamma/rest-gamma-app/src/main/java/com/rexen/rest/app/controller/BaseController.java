package com.rexen.rest.app.controller;

import com.rexen.rest.app.wrap.ServiceStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 控制器基类
 *
 * @author GavinHacker
 * @since 2019-04-18
 */
public abstract class BaseController {

    /**
     * 返回成功信息
     * HTTP状态码：200
     *
     * @return 成功信息
     */
    public ResponseEntity<ServiceStatus> renderSuccess() {
        String message = "Request success";
        return new ResponseEntity<>(ServiceStatus.success(message, ""), HttpStatus.OK);
    }

    /**
     * 返回成功信息
     * HTTP状态码：200
     *
     * @param message 成功说明信息
     * @return 成功信息
     */
    public ResponseEntity<ServiceStatus> renderSuccess(String message) {
        return new ResponseEntity<>(ServiceStatus.success(message, ""), HttpStatus.OK);
    }

    /**
     * 返回成功信息
     * HTTP状态码：200
     *
     * @param data 返回的数据信息对象
     * @return 成功信息
     */
    public ResponseEntity<ServiceStatus> renderSuccess(Object data) {
        String message = "Request success";
        return new ResponseEntity<>(ServiceStatus.success(message, data), HttpStatus.OK);
    }

    /**
     * 返回成功信息
     * HTTP状态码：200
     *
     * @param message 成功说明信息
     * @param data 返回的数据对象
     * @return 成功信息
     */
    public ResponseEntity<ServiceStatus> renderSuccess(String message, Object data) {
        return new ResponseEntity<>(ServiceStatus.success(message, data), HttpStatus.OK);
    }

    /**
     * 返回成功信息
     * HTTP状态码：200
     *
     * @param message 成功说明信息
     * @param data 返回的数据对象
     * @param extension 扩展信息
     * @return 成功信息
     */
    public ResponseEntity<ServiceStatus> renderSuccess(String message, Object data, String extension) {
        return new ResponseEntity<>(new ServiceStatus(ServiceStatus.Status.success, message, data, extension), HttpStatus.OK);
    }

    /**
     * 返回失败信息
     * HTTP状态码：206
     *
     * @param message 失败说明信息
     * @return 失败信息
     */
    public ResponseEntity<ServiceStatus> renderFail(String message) {
        return new ResponseEntity<>(ServiceStatus.fail(message, ServiceStatus.Status.fail), HttpStatus.PARTIAL_CONTENT);
    }


    /**
     * 返回资源未找到
     * HTTP状态码：404
     *
     * @return 资源未找到
     */
    public ResponseEntity<ServiceStatus> renderNotFound() {
        return renderNotFound("指定资源未找到");
    }

    /**
     * 返回资源未找到
     * HTTP状态码：404
     *
     * @param message 资源未找到原因说明
     * @return 资源未找到
     */
    public ResponseEntity<ServiceStatus> renderNotFound(String message) {
        return new ResponseEntity<>(ServiceStatus.fail(message, ServiceStatus.Status.notfound), HttpStatus.NOT_FOUND);
    }

    /**
     * 返回请求信息错误
     * HTTP状态码：400
     *
     * @return 请求信息错误
     */
    public ResponseEntity<ServiceStatus> renderBadRequest() {
        return renderBadRequest("请求信息错误");
    }

    /**
     * 返回请求信息错误
     * HTTP状态码：400
     *
     * @param message 请求信息错误原因说明
     * @return 请求信息错误
     */
    public ResponseEntity<ServiceStatus> renderBadRequest(String message) {
        return new ResponseEntity<>(ServiceStatus.fail(message, ServiceStatus.Status.badrequest), HttpStatus.BAD_REQUEST);
    }

    /**
     * 返回服务端处理错误
     * HTTP状态码：500
     *
     * @return 服务端处理错误
     */
    public ResponseEntity<ServiceStatus> renderServerError() {
        return renderServerError("服务器处理错误");
    }

    /**
     * 返回服务端处理错误
     * HTTP状态码：500
     *
     * @param message 请求信息错误原因说明
     * @return 服务端处理错误
     */
    public ResponseEntity<ServiceStatus> renderServerError(String message) {
        return new ResponseEntity<>(ServiceStatus.fail(message, ServiceStatus.Status.error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 返回认证失败错误
     * HTTP状态码：401
     *
     * @return 认证失败返回数据
     */
    public ResponseEntity<ServiceStatus> renderUnauthorized(){
        return renderUnauthorized("认证失败");
    }

    /**
     * 返回认证失败错误
     * HTTP状态码：401
     *
     * @param message 认证失败的具体信息
     * @return 认证失败返回数据
     */
    public ResponseEntity<ServiceStatus> renderUnauthorized(String message){
        return new ResponseEntity<>(ServiceStatus.fail(message, ServiceStatus.Status.denied), HttpStatus.UNAUTHORIZED);
    }
}
