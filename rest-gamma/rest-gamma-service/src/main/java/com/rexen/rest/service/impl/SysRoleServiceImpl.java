package com.rexen.rest.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rexen.rest.mapper.SysRoleMapper;
import com.rexen.rest.model.entity.SysRole;
import com.rexen.rest.model.entity.SysRoleMenu;
import com.rexen.rest.model.entity.SysUserRole;
import com.rexen.rest.model.entity.page.MyPage;
import com.rexen.rest.service.SysRoleMenuService;
import com.rexen.rest.service.SysRoleService;
import com.rexen.rest.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    /**
     * 角色分页列表
     * @param myPage
     * @return
     */
    @Override
    public Page<SysRole> selectSysRolePage(MyPage<SysRole> myPage){
        List<SysRole> sysRoleMyPage = baseMapper.selectSysRoleList(myPage);
        myPage.setRecords(sysRoleMyPage);
        return myPage;
    }

    /**
     * 根据角色id获取菜单id列表
     * @param roleId
     * @return
     */
    @Override
    public List<String> queryMenuIdList(String roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    /**
     * 保存角色和角色菜单关联关系
     * @param sysRole
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveRole(SysRole sysRole) {
        String roleId = UUID.randomUUID().toString();
        sysRole.setRoleId(roleId);
        Integer result = baseMapper.insert(sysRole);
        //批量添加角色
        batchAddRoleMenu(sysRole);
        return result;
    }

    /**
     * 更新角色、角色菜单关联关系
     * @param sysRole
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateRole(SysRole sysRole) {
        Integer result = baseMapper.updateById(sysRole);
        //删除之前的角色菜单关联关系
        Wrapper<SysRoleMenu> wrapper = new QueryWrapper<SysRoleMenu>().eq("role_id", sysRole.getRoleId());
        sysRoleMenuService.remove(wrapper);
        //批量添加新的角色菜单关联关系
        batchAddRoleMenu(sysRole);
        return result;
    }

    /**
     * 批量添加角色菜单关联
     * @param sysRole
     */
    private void batchAddRoleMenu(SysRole sysRole) {
        String[] menuIdList = sysRole.getMenuIdList();
        if(menuIdList != null){
            for (String menuId: menuIdList) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(UUID.randomUUID().toString());
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenuService.save(sysRoleMenu);
            }
        }
    }

    /**
     * 删除角色、角色用户关联关系、角色菜单关联关系
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteRole(String roleId) {
        //角色用户关联关系
        Wrapper<SysUserRole> wrapperUserRole = new QueryWrapper<SysUserRole>().eq("role_id", roleId);
        sysUserRoleService.remove(wrapperUserRole);
        //角色菜单关联关系
        Wrapper<SysRoleMenu> wrapperRoleMenu = new QueryWrapper<SysRoleMenu>().eq("role_id", roleId);
        sysRoleMenuService.remove(wrapperRoleMenu);
        //删除角色
        return baseMapper.deleteById(roleId);
    }
}
