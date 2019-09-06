package com.rexen.rest.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rexen.rest.common.annotation.RestToken;
import com.rexen.rest.common.util.ShiroUtils;
import com.rexen.rest.mapper.SysUserMapper;
import com.rexen.rest.model.entity.SysUser;
import com.rexen.rest.model.entity.SysUserRole;
import com.rexen.rest.model.entity.page.MyPage;
import com.rexen.rest.service.SysUserRoleService;
import com.rexen.rest.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserRoleService sysUserRoleService;

    /**
     * 禁用
     */
    private final Integer STATUS_FORBIDDEN = 0;
    /**
     * 正常
     */
    private final Integer STATUS_NORMAL = 1;

    @Override
    public Page<SysUser> selectSysUserPage(MyPage<SysUser> myPage, Integer status){
        List<SysUser> sysUserMyPage = baseMapper.selectSysUserList(myPage, status);
        myPage.setRecords(sysUserMyPage);
        return myPage;
    }

    /**
     * 添加用户并维护用户角色关系
     * @param sysUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertUserAndUserRole(SysUser sysUser) {
        //生成userId,sysUser主键
        String userId = UUID.randomUUID().toString();
        sysUser.setUserId(userId);
        sysUser.setSalt(RestToken.class.getSimpleName());
        sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(), sysUser.getSalt()));
        sysUser.setStatus(STATUS_NORMAL);
        //插入user
        Integer addUserCount = this.baseMapper.insert(sysUser);
        //循环插入用户角色关系
        batchAddUserRoleToUserId(sysUser, userId);
        return addUserCount;
    }

    /**
     * 更新用户并维护用户角色关系
     * @param sysUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUserAndUserRole(SysUser sysUser) {
        String userId = sysUser.getUserId();
        //盐
        String salt = baseMapper.selectById(sysUser).getSalt();
        sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(), salt));
        //更新用户sysUser数据
        Integer updateUserCount = this.baseMapper.updateById(sysUser);
        //删除之前的用户角色关系数据
        Wrapper<SysUserRole> wrapper = new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getUserId());
        sysUserRoleService.remove(wrapper);
        //循环插入新的用户角色关系
        batchAddUserRoleToUserId(sysUser, userId);
        return updateUserCount;
    }

    /**
     * 查询用户下所有菜单id
     * @param userId
     * @return
     */
    @Override
    public List<String> queryAllMenuId(String userId) {
        return baseMapper.selectMenuIdsToUserId(userId);
    }

    /**
     * 循环插入用户角色关系
     * @param sysUser
     * @param userId
     */
    private void batchAddUserRoleToUserId(SysUser sysUser, String userId) {
        String[] roleIds = sysUser.getRoleIds();
        if(roleIds != null){
            for (String roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setId(UUID.randomUUID().toString());
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                sysUserRoleService.save(sysUserRole);
            }
        }
    }

    /**
     * 删除用户以及用户角色关联关系
     * @param userId
     * @return
     */
    @Override
    public Integer deleteInfo(String userId) {
        Wrapper<SysUserRole> wrapper = new QueryWrapper<SysUserRole>().eq("user_id", userId);
        //删除关联关系
        sysUserRoleService.remove(wrapper);
        //删除用户
        return baseMapper.deleteById(userId);
    }
}
