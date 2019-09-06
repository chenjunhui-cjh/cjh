package com.rexen.rest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rexen.rest.model.entity.SysOperationLog;

/**
 * <p>
 * 系统操作日志 Mapper 接口
 * </p>
 *
 * @author GavinHacker
 * @since 2018-03-02
 */
public interface SysOperationLogMapper extends BaseMapper<SysOperationLog> {

    public SysOperationLog selectOperationLogById(String id);
}
