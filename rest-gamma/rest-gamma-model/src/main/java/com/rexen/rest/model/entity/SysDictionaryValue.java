package com.rexen.rest.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 字典值表
 * </p>
 *
 * @author Gavin
 * @since 2019-05-10
 */
@ApiModel(description = "字典值表")
public class SysDictionaryValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 字典类型ID
     */
    @ApiModelProperty(value = "字典类型ID")
    private String typeId;
    /**
     * 字典值name
     */
    @ApiModelProperty(value = "字典值name")
    private String name;
    /**
     * 字典值value
     */
    @ApiModelProperty(value = "字典值value")
    private String value;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer sort;


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
     * 获取字典类型ID
     *
     * @return 字典类型ID
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置字典类型ID
     *
     * @param typeId 字典类型ID
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取字典值name
     *
     * @return 字典值name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典值name
     *
     * @param name 字典值name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取字典值value
     *
     * @return 字典值value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置字典值value
     *
     * @param value 字典值value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取排序号
     *
     * @return 排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序号
     *
     * @param sort 排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SysDictionaryValue{" +
        ", id=" + id +
        ", typeId=" + typeId +
        ", name=" + name +
        ", value=" + value +
        ", sort=" + sort +
        "}";
    }
}
