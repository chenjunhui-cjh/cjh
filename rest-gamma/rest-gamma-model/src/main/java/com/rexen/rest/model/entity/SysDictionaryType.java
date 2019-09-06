package com.rexen.rest.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author Gavin
 * @since 2019-05-10
 */
@ApiModel(description = "字典类型表")
public class SysDictionaryType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 类型码
     */
    @ApiModelProperty(value = "类型码")
    private String code;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 父节级字典ID
     */
    @ApiModelProperty(value = "父节级字典ID")
    private String parentId;


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
     * 获取类型码
     *
     * @return 类型码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置类型码
     *
     * @param code 类型码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取描述
     *
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取父节级字典ID
     *
     * @return 父节级字典ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父节级字典ID
     *
     * @param parentId 父节级字典ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysDictionaryType{" +
        ", id=" + id +
        ", code=" + code +
        ", description=" + description +
        ", parentId=" + parentId +
        "}";
    }
}
