package com.rexen.rest.app.controller;


import com.rexen.rest.annotation.FunctionLog;
import com.rexen.rest.app.constant.QueryPaginationConstant;
import com.rexen.rest.app.wrap.ServiceStatus;
import com.rexen.rest.model.entity.SysMenu;
import com.rexen.rest.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
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
 * 菜单管理 前端控制器
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {

    @Autowired
    SysMenuService sysMenuService;

    @FunctionLog(module = "perm", operation = "导航菜单")
    @RequestMapping(value = "nav", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> nav(String userId){
        return renderSuccess(sysMenuService.getUserMenuList(userId));
    }

    @FunctionLog(module = "perm", operation = "所有菜单列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> list(){
        return renderSuccess(sysMenuService.getAllMenuList());
    }

    @FunctionLog(module = "perm", operation = "选择菜单(添加、修改菜单)")
    @RequestMapping(value = "select", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> select(){
        return renderSuccess(sysMenuService.getSelectMenuList());
    }

    @FunctionLog(module = "perm", operation = "菜单详细")
    @RequestMapping(value = "queryInfo", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryInfo(String menuId){
        return renderSuccess(sysMenuService.getById(menuId));
    }

    @FunctionLog(module = "perm", operation = "保存")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> save(@RequestBody SysMenu sysMenu){
        String message = verifyForm(sysMenu);
        if(message == null){
            sysMenu.setMenuId(UUID.randomUUID().toString());
            return renderSuccess(sysMenuService.save(sysMenu));
        }
        return renderFail(message);
    }

    @FunctionLog(module = "perm", operation = "修改")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> update(@RequestBody SysMenu sysMenu){
        String message = verifyForm(sysMenu);
        if(message == null){
            return renderSuccess(sysMenuService.updateById(sysMenu));
        }
        return renderFail(message);
    }

    @FunctionLog(module = "perm", operation = "删除")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> delete(String menuId){
        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return renderFail("请先删除子菜单或按钮");
        }
        return renderSuccess(sysMenuService.removeById(menuId));
    }

    /**
     * 验证参数是否正确
     */
    private String verifyForm(SysMenu menu){
        if(StringUtils.isBlank(menu.getName())){
            return "菜单名称不能为空";
        }

        if(menu.getParentId() == null){
            return "上级菜单不能为空";
        }

        //菜单
        if(menu.getType() == QueryPaginationConstant.MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                return "菜单URL不能为空";
            }
        }

        //上级菜单类型
        int parentType = QueryPaginationConstant.MenuType.CATALOG.getValue();
        if(!(menu.getParentId().equals("0"))){
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());

            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == QueryPaginationConstant.MenuType.CATALOG.getValue() ||
                menu.getType() == QueryPaginationConstant.MenuType.MENU.getValue()){
            if(parentType != QueryPaginationConstant.MenuType.CATALOG.getValue()){
                return "上级菜单只能为目录类型";
            }
            return null;
        }

        //按钮
        if(menu.getType() == QueryPaginationConstant.MenuType.BUTTON.getValue()){
            if(parentType != QueryPaginationConstant.MenuType.MENU.getValue()){
                return "上级菜单只能为菜单类型";
            }
        }
        return null;
    }
}

