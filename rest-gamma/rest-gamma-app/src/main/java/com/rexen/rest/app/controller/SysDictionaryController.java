package com.rexen.rest.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rexen.rest.annotation.FunctionLog;
import com.rexen.rest.app.wrap.ServiceStatus;
import com.rexen.rest.common.annotation.FieldName;
import com.rexen.rest.model.entity.SysDictionaryType;
import com.rexen.rest.model.entity.SysDictionaryValue;
import com.rexen.rest.model.enumtype.DicType;
import com.rexen.rest.model.vo.DicValuesVO;
import com.rexen.rest.service.SysDictionaryTypeService;
import com.rexen.rest.service.SysDictionaryValueService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author Gavin
 * @since 2019-05-10
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/dictionary")
public class SysDictionaryController extends BaseController {

    Logger logger = Logger.getLogger(SysDictionaryController.class);

    @Autowired
    SysDictionaryValueService sysDictionaryValueService;

    @Autowired
    SysDictionaryTypeService sysDictionaryTypeService;

    /**
     * 根据类型查询数据字典
     *
     * @author GavinHacker
     * */
    @FunctionLog(module = "字典模块", operation = "根据类型查询数据字典")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> get(String typeId){
        final String TYPE_ID_NAME = "type_id";
        QueryWrapper<SysDictionaryValue> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(TYPE_ID_NAME, typeId);
        return renderSuccess(sysDictionaryValueService.list(QueryWrapper));
    }

    /**
     * 查询所有的数据字典
     *
     * @author GavinHacker
     * */
    @FunctionLog(module = "字典模块", operation = "查询所有的数据字典")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> get(){
        QueryWrapper<SysDictionaryValue> QueryWrapper = new QueryWrapper<>();
        return renderSuccess(sysDictionaryValueService.list(QueryWrapper));
    }

    /**
     * 根据字典类型码查询字典类型
     *
     * @author GavinHacker
     * */
    @FunctionLog(module = "字典模块", operation = "根据字典类型码查询字典类型")
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> getType(String code){
        final String CODE_FIELD_NAME = "code";
        QueryWrapper<SysDictionaryType> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(CODE_FIELD_NAME, code);
        return renderSuccess(sysDictionaryTypeService.list(QueryWrapper));
    }

    /**
     * 查询字典所有类型
     *
     * @author GavinHacker
     * */
    @FunctionLog(module = "字典模块", operation = "查询字典所有类型")
    @RequestMapping(value = "/type/all", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> getType(){
        QueryWrapper<SysDictionaryType> QueryWrapper = new QueryWrapper<>();
        return renderSuccess(sysDictionaryTypeService.list(QueryWrapper));
    }

    /**
     * 根据字典类型名称，获取字典项值
     * @param dicType 字典类名称
     * @return 字典项内容
     * @author Li Ze
     */
    @FunctionLog(module = "字典模块", operation = "根据类型查询数据字典")
    @RequestMapping(value = "/{dicType}", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> getByType(@PathVariable(value = "dicType") String dicType){
        if(StringUtils.isEmpty(dicType)){
            return renderFail("字典名不能为空");
        }
        try{
            DicType dicTypeItem = DicType.valueOf(dicType.toUpperCase().replaceAll("-","_"));
            String typeId = dicTypeItem.getCode();
            List<DicValuesVO> values = sysDictionaryValueService.getValuesByType(typeId);
            return renderSuccess("查询字典项成功",values);
        }catch (Exception e){
            return renderFail("该字典项不存在");
        }
    }
}

