package com.rexen.rest.app.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import com.alibaba.fastjson.JSONObject;
import com.rexen.rest.model.entity.SysOperationLog;
import com.rexen.rest.model.entity.SysOperationParam;
import com.rexen.rest.service.impl.SysOperationLogServiceImpl;
import com.rexen.rest.service.impl.SysOperationParamServiceImpl;
import org.apache.log4j.Logger;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author: GavinHacker
 * @description:
 * @date: Created in 下午5:23 2019/3/20
 * @modifiedBy:
 */
@Component("logHandlerActor")
@Scope("prototype")
public class LogHandlerActor extends AbstractActor {

    Logger logger = Logger.getLogger(LogHandlerActor.class);

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SysOperationLogServiceImpl systemOperationLogService;

    @Autowired
    private SysOperationParamServiceImpl systemOperationParamService;

    @Transactional(rollbackFor = Exception.class)
    public void insertFunctionLog(Quartet<String, String, String, Object> content){
        SysOperationLog sysOperationLog = new SysOperationLog();
        String mId = UUID.randomUUID().toString();
        sysOperationLog.setId(mId);

        try {
            sysOperationLog.setClientIp(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            logger.warn("获取ip异常".concat(e.getMessage()));
        }
        sysOperationLog.setModule(content.getValue0());
        sysOperationLog.setOperationType(content.getValue1());
        sysOperationLog.setUserId("admin");
        sysOperationLog.setUserName("管理员");
        systemOperationLogService.save(sysOperationLog);
        SysOperationParam sysOperationParam = new SysOperationParam();
        sysOperationParam.setId(UUID.randomUUID().toString());
        sysOperationParam.setLogId(mId);
        sysOperationParam.setRequestParam(JSONObject.toJSONString(content.getValue3()));
        systemOperationParamService.save(sysOperationParam);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Quartet.class, x -> {
            try {
                insertFunctionLog(x);
                logger.info("Record the function log successfully.");
            }catch (Exception e){
                logger.error("插入日志错误(Insert function log error):", e);
            }
        }).build();
    }
}
