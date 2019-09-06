package com.rexen.rest.app.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rexen.rest.model.entity.SysUser;
import com.rexen.rest.service.impl.SysUserServiceImpl;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import static com.rexen.rest.app.cache.SessionCacheManager.setAttribute;

/**
 * @author: GavinHacker
 * @description:
 * @date: Created in 下午1:19 18/3/3
 * @modifiedBy:
 */
public class RestRealm extends AuthorizingRealm {

    static Logger logger = Logger.getLogger(RestRealm.class);

    @Autowired
    private SysUserServiceImpl systemUser;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        try {
            /*Role和Permission逻辑按需添加*/
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            simpleAuthorInfo.addStringPermission("all");
            return simpleAuthorInfo;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        this.setCachingEnabled(true);
        this.setAuthenticationCachingEnabled(true);
        this.setAuthorizationCachingEnabled(true);
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        logger.info("NOTE: current token host => " + token.getHost());
        try {
            if(token.getPrincipal() == null) {
                return null;
            }
            SysUser user = this.systemUser.getOne(new QueryWrapper<SysUser>().eq("username", token.getUsername()));
            if(user == null){
                return null;
            }
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
            setAttribute("currentUserName", user.getUsername());
            return authenticationInfo;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}