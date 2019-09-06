package com.rexen.rest.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@ApiModel(description = "部门管理")
@TableName("sys_dept")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId("id")
    private String deptId;
    /**
     * 上级部门ID，一级部门为0
     */
    @ApiModelProperty(value = "上级部门ID，一级部门为0")
    private String parentId;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNum;
    /**
     * 是否删除  -1：已删除  0：正常
     */
    @ApiModelProperty(value = "是否删除  -1：已删除  0：正常")
    private Integer delFlag;


    /**
     * 获取
     *
     * @return 
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置
     *
     * @param deptId 
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取上级部门ID，一级部门为0
     *
     * @return 上级部门ID，一级部门为0
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级部门ID，一级部门为0
     *
     * @param parentId 上级部门ID，一级部门为0
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取部门名称
     *
     * @return 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     *
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * 获取是否删除  -1：已删除  0：正常
     *
     * @return 是否删除  -1：已删除  0：正常
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否删除  -1：已删除  0：正常
     *
     * @param delFlag 是否删除  -1：已删除  0：正常
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "SysDept{" +
        ", deptId=" + deptId +
        ", parentId=" + parentId +
        ", name=" + name +
        ", orderNum=" + orderNum +
        ", delFlag=" + delFlag +
        "}";
    }
}
