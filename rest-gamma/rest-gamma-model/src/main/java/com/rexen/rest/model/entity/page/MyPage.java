package com.rexen.rest.model.entity.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author miemie
 * @since 2018-08-10
 */
//@Data
//@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {
    private static final long serialVersionUID = 5194933845448697148L;

    private static final int SIZE = 3;

    public Integer getSelectInt() {
        return selectInt;
    }

    public void setSelectInt(Integer selectInt) {
        this.selectInt = selectInt;
    }

    public String getSelectStr() {
        return selectStr;
    }

    public void setSelectStr(String selectStr) {
        this.selectStr = selectStr;
    }

    private Integer selectInt;
    private String selectStr;

    public MyPage(int current) {
        super(current, SIZE);
    }
}
