package com.rexen.rest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rexen.rest.mapper.SysDictionaryValueMapper;
import com.rexen.rest.model.entity.SysDictionaryValue;
import com.rexen.rest.model.vo.DicValuesVO;
import com.rexen.rest.service.SysDictionaryValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典值表 服务实现类
 * </p>
 *
 * @author Gavin
 * @since 2019-05-10
 */
@Service
public class SysDictionaryValueServiceImpl extends ServiceImpl<SysDictionaryValueMapper, SysDictionaryValue> implements SysDictionaryValueService {


    @Override
    public List<DicValuesVO> getValuesByType(String typeId){
        return baseMapper.getValuesByType(typeId);
    }
}
