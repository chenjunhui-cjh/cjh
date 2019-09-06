package com.rexen.rest.model.vo;

/**
 * 字典项内容
 *
 * @author Li Ze
 */
public class DicValuesVO {

    /**字典值id*/
    private String id;

    /**字典值*/
    private String value;

    /**字典值名称*/
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
