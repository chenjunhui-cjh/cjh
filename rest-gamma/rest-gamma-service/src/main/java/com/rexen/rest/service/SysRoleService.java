package com.rexen.rest.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rexen.rest.model.entity.SysRole;
import com.rexen.rest.model.entity.page.MyPage;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取角色分页列表
     */
    Page<SysRole> selectSysRolePage(MyPage<SysRole> page);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<String> queryMenuIdList(String roleId);

    /**
     * 保存角色和角色菜单关联关系
     * @param sysRole
     */
    Integer saveRole(SysRole sysRole);

    /**
     * 更新角色和角色菜单关联关系
     * @param sysRole
     */
    Integer updateRole(SysRole sysRole);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Integer deleteRole(String roleId);
}
