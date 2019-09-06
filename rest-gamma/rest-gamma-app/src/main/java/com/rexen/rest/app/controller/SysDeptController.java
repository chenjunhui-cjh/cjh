package com.rexen.rest.app.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rexen.rest.annotation.FunctionLog;
import com.rexen.rest.app.wrap.ServiceStatus;
import com.rexen.rest.model.entity.SysDept;
import com.rexen.rest.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Controller
@RequestMapping("/sysDept")
public class SysDeptController extends BaseController {

    @Autowired
    SysDeptService sysDeptService;

    @FunctionLog(module = "perm", operation = "删除部门")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> delete(String deptId){
        //判断是否有子部门
        Wrapper<SysDept> wrapper = new QueryWrapper<SysDept>().eq("parent_id", deptId);
        List<SysDept> deptList = sysDeptService.list(wrapper);
        if(deptList.size() > 0){
            return renderFail("请先删除子部门");
        }
        return renderSuccess(sysDeptService.removeById(deptId));
    }

    @FunctionLog(module = "perm", operation = "所有下级部门")
    @RequestMapping(value = "childDept", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> childDept(String parentId){
        //所有下级部门
        Wrapper<SysDept> wrapper = new QueryWrapper<SysDept>().eq("parent_id", parentId);
        return renderSuccess(sysDeptService.list(wrapper));
    }

    @FunctionLog(module = "perm", operation = "部门列表")
    @RequestMapping(value = "queryList", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryList(){
        Wrapper<SysDept> wrapper = new QueryWrapper<SysDept>();
        return renderSuccess(sysDeptService.list(wrapper));
    }

    @FunctionLog(module = "perm", operation = "新增部门")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> save(@RequestBody SysDept dept){
        dept.setDeptId(UUID.randomUUID().toString());
        return renderSuccess(sysDeptService.save(dept));
    }

    @FunctionLog(module = "perm", operation = "修改部门")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> update(@RequestBody SysDept dept){
        return renderSuccess(sysDeptService.updateById(dept));
    }

}

