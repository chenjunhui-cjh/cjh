package com.rexen.rest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rexen.rest.mapper.SysUserRoleMapper;
import com.rexen.rest.model.entity.SysUserRole;
import com.rexen.rest.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
