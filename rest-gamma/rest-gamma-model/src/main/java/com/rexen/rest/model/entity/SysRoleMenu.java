package com.rexen.rest.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@ApiModel(description = "角色与菜单对应关系")
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId("id")
    private String id;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;
    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private String menuId;


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

    /**
     * 获取菜单ID
     *
     * @return 菜单ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单ID
     *
     * @param menuId 菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        "}";
    }
}
