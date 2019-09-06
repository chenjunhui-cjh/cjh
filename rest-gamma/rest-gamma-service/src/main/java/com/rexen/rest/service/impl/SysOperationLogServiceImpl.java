package com.rexen.rest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rexen.rest.mapper.SysOperationLogMapper;
import com.rexen.rest.model.entity.SysOperationLog;
import com.rexen.rest.service.SysOperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author GavinHacker
 * @since 2018-03-02
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements SysOperationLogService {

    public SysOperationLog selectOperationLogById(String id){
        SysOperationLog s = new SysOperationLog();
        s.setId(id);
        return baseMapper.selectOperationLogById(id);
    }
}
