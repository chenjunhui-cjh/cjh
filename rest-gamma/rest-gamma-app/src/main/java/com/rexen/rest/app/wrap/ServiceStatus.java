package com.rexen.rest.app.wrap;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * Controller返回信息包装对象
 *
 * @author Gavin
 */
@ApiModel(description = "返回信息包装对象")
public class ServiceStatus {

    /**
     * 主键标识
     */
    @ApiModelProperty(value = "主键标识")
    private String id;

    /**
     * 结果状态
     */
    @ApiModelProperty(value = "结果状态 1:成功状态 2:失败状态 3:验证权限失败状态 4:未知状态")
    private int status;

    /**
     * 结果信息
     */
    @ApiModelProperty(value = "结果信息")
    private String message;

    /**
     * 结果数据
     */
    @ApiModelProperty(value = "结果数据")
    private Object data;

    /**
     * 结果时间戳
     */
    @ApiModelProperty(value = "结果时间戳")
    private Long timestamp;

    /**
     * 结果扩展信息
     */
    @ApiModelProperty(value = "结果扩展信息")
    private String extension;

    public ServiceStatus(Status status, String message, Object data, String ... ex){
        this.id = generateServiceStatusId();
        this.status = status.status;
        this.message = message;
        this.data = data;
        this.extension = Arrays.stream(ex).reduce((list1, list2) -> list1.concat("$#").concat(list2)).orElse("");
    }

    private String generateServiceStatusId(){
        String hostInfo = StringUtils.EMPTY;
        try {
            InetAddress i = InetAddress.getLocalHost();
            if(i != null) {
                hostInfo = i.toString();
            }
            hostInfo = "[".concat(hostInfo).concat("]");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String id = String.valueOf(RandomUtils.nextInt(100000, 999999));
        return "[".concat(id).concat("]").concat(hostInfo);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getId() {
        if(StringUtils.isBlank(this.id)){
            this.id = generateServiceStatusId();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        if(this.timestamp == null){
            this.timestamp = DateUtil.current(false);
        }
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

    /**
     * 返回成功信息
     *
     * @return 成功信息
     */
    public static ServiceStatus success() {
        return success("success");
    }

    /**
     * 返回成功信息
     *
     * @param message 成功说明信息
     * @return 成功信息
     */
    public static ServiceStatus success(String message) {
        return success(message, null);
    }

    /**
     * 返回成功信息
     *
     * @param data 返回的数据信息
     * @return 成功信息
     */
    public static ServiceStatus success(Object data) {
        return success("success", data);
    }

    /**
     * 返回成功信息
     *
     * @param message 成功说明信息
     * @param data 返回的数据信息
     * @return 成功信息
     */
    public static ServiceStatus success(String message, Object data) {
        return new ServiceStatus(Status.success, message, data);
    }

    /**
     * 返回失败信息
     *
     * @param message 失败原因说明
     * @return 失败信息
     */
    public static ServiceStatus fail(String message, Status status) {
        return new ServiceStatus(status, message, null);
    }

    @ApiModel(description = "返回信息状态枚举")
    public enum Status {

        /**
         * 成功状态
         */
        success(200),

        /**
         * 失败状态
         */
        fail(206),

        /**
         * 验证权限失败状态
         */
        denied(401),

        /**
         * 资源未找到
         */
        notfound(404),

        /**
         * 错误请求
         */
        badrequest(400),

        /**
         * 未知状态
         */
        error(500);

        private int status;

        Status(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return  new Integer(this.status).toString();
        }
    }
}
