package com.rexen.rest.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 用户与角色对应关系
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@ApiModel(description = "用户与角色对应关系")
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId("id")
    private String id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;


    /**
     * 获取
     *
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     *
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取角色ID
     *
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
        ", id=" + id +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
