package com.rexen.rest.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@ApiModel(description = "角色")
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId("id")
    private String roleId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private String deptId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(exist=false)
    private String[] menuIdList;

    public String[] getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(String[] menuIdList) {
        this.menuIdList = menuIdList;
    }

    /**
     * 获取
     *
     * @return 
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置
     *
     * @param roleId 
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取部门ID
     *
     * @return 部门ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        ", roleId=" + roleId +
        ", roleName=" + roleName +
        ", remark=" + remark +
        ", deptId=" + deptId +
        ", createTime=" + createTime +
        "}";
    }
}
