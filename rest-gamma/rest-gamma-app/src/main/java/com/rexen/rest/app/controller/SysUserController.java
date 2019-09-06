package com.rexen.rest.app.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rexen.rest.annotation.FunctionLog;
import com.rexen.rest.app.wrap.ServiceStatus;
import com.rexen.rest.common.annotation.RestToken;
import com.rexen.rest.common.util.ShiroUtils;
import com.rexen.rest.model.entity.SysUser;
import com.rexen.rest.model.entity.page.MyPage;
import com.rexen.rest.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author Duliming
 * @since 2019-04-11
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value="Post登录", notes="登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @FunctionLog(module = "系统用户模块", operation = "用户登录操作", extension = "none")
    public ResponseEntity<ServiceStatus> loginPost(@RequestBody @Valid SysUser user){
        Subject currentUserSubject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                ShiroUtils.sha256(user.getPassword(), RestToken.class.getSimpleName()));
        currentUserSubject.login(usernamePasswordToken);
        return new ResponseEntity<ServiceStatus>(new ServiceStatus(ServiceStatus.Status.success, "login success", user.getUsername()), HttpStatus.OK);
    }

    @FunctionLog(module = "系统用户模块", operation = "新增用户")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> save(@RequestBody SysUser user){
        return renderSuccess(sysUserService.insertUserAndUserRole(user));
    }

    @FunctionLog(module = "系统用户模块", operation = "修改用户")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<ServiceStatus> update(@RequestBody SysUser user){
        return renderSuccess(sysUserService.updateUserAndUserRole(user));
    }

    @FunctionLog(module = "系统用户模块", operation = "删除用户")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> delete(String userId){
        return renderSuccess(sysUserService.deleteInfo(userId));
    }

    @FunctionLog(module = "系统用户模块", operation = "查询用户")
    @RequestMapping(value = "queryInfo", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryInfo(String userId){
        return renderSuccess(sysUserService.getById(userId));
    }

    @FunctionLog(module = "系统用户模块", operation = "查询用户列表分页")
    @RequestMapping(value = "queryPage", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryPage(int pageCount, Integer status){
        MyPage page = new MyPage(pageCount);
        return renderSuccess(sysUserService.selectSysUserPage(page, status));
    }

    @FunctionLog(module = "系统用户模块", operation = "查询用户列表")
    @RequestMapping(value = "queryList", method = RequestMethod.GET)
    public ResponseEntity<ServiceStatus> queryList(){
        Wrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        return renderSuccess(sysUserService.list(wrapper));
    }

    @ApiOperation(value="登录{get操作}", notes="用于shiro的接口拦截跳转")
    @RequestMapping(value = "shiroVerify", method = RequestMethod.GET)
    @FunctionLog(module = "权限", operation = "系统提示用户登录", extension = "用于shiro登录拦截，未登录状态的跳转地址")
    public ResponseEntity<ServiceStatus> shiroVerify(){
        return new ResponseEntity<ServiceStatus>(new ServiceStatus(ServiceStatus.Status.success, "请登录后再访问接口", ""), HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation(value="Get登录", notes="get登录接口为了开发调试方便而设，约定正式的登录逻辑使用post, 此接口后期删除")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @FunctionLog(module = "系统用户模块", operation = "用户登录操作", extension = "none")
    public ResponseEntity<ServiceStatus> loginGet(String username, String password){
        Subject currentUserSubject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                ShiroUtils.sha256(password, RestToken.class.getSimpleName()));
        currentUserSubject.login(usernamePasswordToken);
        return new ResponseEntity<ServiceStatus>(new ServiceStatus(ServiceStatus.Status.success, "login success", username, "Get登录接口为了开发调试便利而设，约定正式的登录逻辑使用post, 此接口将是不稳定接口，可能被删除。"), HttpStatus.OK);
    }
}

