package com.rexen.rest.service;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyMetaObjectHandler
 * @author Gavin
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    protected final static Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        //logger.debug("新增监听");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //logger.debug("更新监听");
    }
}
