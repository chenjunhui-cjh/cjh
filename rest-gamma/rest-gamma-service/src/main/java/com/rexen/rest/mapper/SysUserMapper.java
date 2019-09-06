package com.rexen.rest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rexen.rest.model.entity.SysUser;
import com.rexen.rest.model.entity.page.MyPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 用户分页列表
     * @param myPage
     * @param status
     * @return
     */
    List<SysUser> selectSysUserList(@Param("myPage") MyPage<SysUser> myPage, @Param("status") Integer status);

    /**
     * 根据用户id查询菜单id集合
     * @param userId
     * @return
     */
    List<String> selectMenuIdsToUserId(String userId);
}
