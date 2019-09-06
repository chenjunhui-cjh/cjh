package com.rexen.rest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rexen.rest.model.entity.SysRole;
import com.rexen.rest.model.entity.page.MyPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 角色分页
     * @param myPage
     * @return
     */
    List<SysRole> selectSysRoleList(@Param("myPage") MyPage<SysRole> myPage);

    /**
     * 根据角色获取菜单列表Id
     * @param roleId
     * @return
     */
    List<String> queryMenuIdList(String roleId);
}
