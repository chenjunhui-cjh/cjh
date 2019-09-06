package com.rexen.rest.model.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@ApiModel(description = "菜单管理")
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId("id")
    private String menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private String parentId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;
    /**
     * 菜单URL
     */
    @ApiModelProperty(value = "菜单URL")
    private String url;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮")
    private Integer type;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    /**
     * 父菜单名称
     */
    @TableField(exist=false)
    private String parentName;

    @TableField(exist=false)
    private Boolean open;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * 获取
     *
     * @return 
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置
     *
     * @param menuId 
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取父菜单ID，一级菜单为0
     *
     * @return 父菜单ID，一级菜单为0
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父菜单ID，一级菜单为0
     *
     * @param parentId 父菜单ID，一级菜单为0
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单名称
     *
     * @return 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单URL
     *
     * @return 菜单URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单URL
     *
     * @param url 菜单URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取类型   0：目录   1：菜单   2：按钮
     *
     * @return 类型   0：目录   1：菜单   2：按钮
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型   0：目录   1：菜单   2：按钮
     *
     * @param type 类型   0：目录   1：菜单   2：按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", menuId=" + menuId +
        ", parentId=" + parentId +
        ", name=" + name +
        ", url=" + url +
        ", type=" + type +
        ", orderNum=" + orderNum +
        "}";
    }
    @TableField(exist=false)
    private List<?> list;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
