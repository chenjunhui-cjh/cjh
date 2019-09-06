package com.rexen.rest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rexen.rest.model.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取用户菜单列表
     * @param userId
     * @return
     */
    public List<SysMenu> getUserMenuList(String userId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    public List<SysMenu> queryListParentId(String parentId);

    /**
     * 所有菜单列表,添加了父菜单的菜单名称
     * @return
     */
    List<SysMenu> getAllMenuList();

    /**
     * 选择菜单(添加、修改菜单)
     * @return
     */
    List<SysMenu> getSelectMenuList();
}
