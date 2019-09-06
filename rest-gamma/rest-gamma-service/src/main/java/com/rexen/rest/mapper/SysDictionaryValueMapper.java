package com.rexen.rest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rexen.rest.model.entity.SysDictionaryValue;
import com.rexen.rest.model.vo.DicValuesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典值表 Mapper 接口
 * </p>
 *
 * @author Gavin
 * @since 2019-05-10
 */
public interface SysDictionaryValueMapper extends BaseMapper<SysDictionaryValue> {

    List<DicValuesVO> getValuesByType(@Param("typeId") String typeId);
}
