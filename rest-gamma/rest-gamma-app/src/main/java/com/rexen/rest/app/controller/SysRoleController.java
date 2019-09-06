package com.rexen.rest.app.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rexen.rest.annotation.FunctionLog;
import com.rexen.rest.app.wrap.ServiceStatus;
import com.rexen.rest.model.entity.SysRole;
import com.rexen.rest.model.entity.page.MyPage;
import com.rexen.rest.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysRoleService sysRoleMenuService;

    @FunctionLog(module = "perm", operation = "查询角色列表")
    @RequestMapping(value = "queryList", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryList(){
        Wrapper<SysRole> wrapper = new QueryWrapper<SysRole>();
        return renderSuccess(sysRoleService.list(wrapper));
    }

    @FunctionLog(module = "perm", operation = "查询角色列表分页")
    @RequestMapping(value = "queryPage", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryPage(int pageCount){
        MyPage page = new MyPage(pageCount);
        return renderSuccess(sysRoleService.selectSysRolePage(page));
    }

    @FunctionLog(module = "perm", operation = "查询角色信息")
    @RequestMapping(value = "queryInfo", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryInfo(String roleId){
        SysRole sysRole = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        //类型转换
        String[] menuIds = new String[menuIdList.size()];
        menuIds = menuIdList.toArray(menuIds);
        sysRole.setMenuIdList(menuIds);
        return renderSuccess(sysRole);
    }

    @FunctionLog(module = "perm", operation = "新增保存角色")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> save(@RequestBody SysRole sysRole){
        return renderSuccess(sysRoleService.saveRole(sysRole));
    }

    @FunctionLog(module = "perm", operation = "更新角色")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> update(@RequestBody SysRole sysRole){
        return renderSuccess(sysRoleService.updateRole(sysRole));
    }

    @FunctionLog(module = "perm", operation = "删除角色")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> delete(String roleId){
        return renderSuccess(sysRoleService.deleteRole(roleId));
    }
}

