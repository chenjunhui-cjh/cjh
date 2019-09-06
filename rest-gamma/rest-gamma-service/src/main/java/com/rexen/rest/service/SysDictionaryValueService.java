package com.rexen.rest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rexen.rest.model.entity.SysDictionaryValue;
import com.rexen.rest.model.vo.DicValuesVO;

import java.util.List;

/**
 * <p>
 * 字典值表 服务类
 * </p>
 *
 * @author Gavin
 * @since 2019-05-10
 */
public interface SysDictionaryValueService extends IService<SysDictionaryValue> {

    List<DicValuesVO> getValuesByType(String typeId);
}
