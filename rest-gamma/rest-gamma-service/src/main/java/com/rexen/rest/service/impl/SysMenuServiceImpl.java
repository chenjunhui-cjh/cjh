package com.rexen.rest.service.impl;

//import com.baomidou.mybatisplus.mapper.QueryWrapper;
//import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rexen.rest.mapper.SysMenuMapper;
import com.rexen.rest.model.entity.SysMenu;
import com.rexen.rest.service.SysMenuService;
import com.rexen.rest.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysUserService sysUserService;

    /**
     * 超级管理员
     */
    private final String SUPER_ADMIN = "1";

    /**
     * 目录
     */
    private final Integer MENU_DIR = 0;
    /**
     * 菜单
     */
    private final Integer MENU_MENU = 1;
    /**
     * 按钮
     */
    private final Integer MENU_BTN = 2;

    @Override
    public List<SysMenu> getUserMenuList(String userId) {
        //系统管理员，拥有最高权限
        if(userId.equals(SUPER_ADMIN)){
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<String> menuIdList){
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId("0", menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 根据父id查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     * @return
     */
    @Override
    public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        //管理员登录时menuIdList为null
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 根据父id查询子菜单
     * @param parentId 父菜单ID
     * @return
     */
    @Override
    public List<SysMenu> queryListParentId(String parentId) {
        Wrapper<SysMenu> wrapper = new QueryWrapper<SysMenu>().eq("parent_id", parentId);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 查询所有菜单列表添加了父菜单名称
     * @return
     */
    @Override
    public List<SysMenu> getAllMenuList() {
        Wrapper<SysMenu> wrapper = new QueryWrapper<SysMenu>();
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        for(SysMenu sysMenuEntity : menuList){
            SysMenu parentMenuEntity = baseMapper.selectById(sysMenuEntity.getParentId());
            if(parentMenuEntity != null){
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }
        return menuList;
    }

    /**
     * 查询root下所有目录和菜单(不包含按钮)
     * @return
     */
    @Override
    public List<SysMenu> getSelectMenuList() {
        //查询列表不是按钮的数据
        Wrapper<SysMenu> wrapper = new QueryWrapper<SysMenu>().ne("type", MENU_BTN);
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId("0");
        root.setName("一级菜单");
        root.setParentId("-1");
        root.setOpen(true);
        menuList.add(root);
        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<String> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();
        for(SysMenu entity : menuList){
            //目录
            if(entity.getType() ==  MENU_DIR){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }
}
