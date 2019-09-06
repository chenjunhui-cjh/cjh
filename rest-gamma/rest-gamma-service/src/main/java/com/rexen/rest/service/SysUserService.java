package com.rexen.rest.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rexen.rest.model.entity.SysUser;
import com.rexen.rest.model.entity.page.MyPage;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
public interface SysUserService extends IService<SysUser> {

    public Page<SysUser> selectSysUserPage(MyPage<SysUser> myPage, Integer status);

    /**
     * 添加用户并维护用户角色关系
     * @param sysUser
     * @return
     */
    public Integer insertUserAndUserRole(SysUser sysUser);

    /**
     * 更新用户并更新用户角色关系
     * @param sysUser
     * @return
     */
    public Integer updateUserAndUserRole(SysUser sysUser);

    /**
     * 查询用户下所有菜单id
     * @param userId
     * @return
     */
    List<String> queryAllMenuId(String userId);

    /**
     * 删除用户以及用户角色关联关系
     * @param userId
     * @return
     */
    Integer deleteInfo(String userId);
}
