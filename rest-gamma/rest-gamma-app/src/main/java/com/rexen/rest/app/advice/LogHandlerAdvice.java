//package com.rexen.rest.app.advice;
//
//import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import com.alibaba.fastjson.JSONObject;
//import com.rexen.rest.annotation.FunctionLog;
//import com.rexen.rest.app.config.akka.SpringExtension;
//import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.javatuples.Quartet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
///**
// * @author: GavinHacker
// * @description:
// * @date: Created in 下午8:22 18/3/1
// * @modifiedBy:
// */
//@Aspect
//@Component
//public class LogHandlerAdvice {
//
//    Logger logger = Logger.getLogger(LogHandlerAdvice.class);
//
//    private ActorRef logHandlerActor;
//
//    @Autowired
//    SpringExtension springExtension;
//
//    @Autowired
//    private ActorSystem actorSystem;
//
//    @Around("functionLoggingAspect()")
//    public Object normalAdvice(ProceedingJoinPoint point) throws Throwable {
//        logger.debug("Start normal advice ...");
//        Object result = null;
//        Long start = System.currentTimeMillis();
//        result = point.proceed();
//        Long end = System.currentTimeMillis();
//        //Long time = end - start;
//
//        Quartet<String, String, String, Object> logContent = null;
//        try {
//            logContent = getFunctionLoggingContent(point);
//        } catch (ClassNotFoundException e1) {
//            logger.error("Error occurred while logging normal advice", e1);
//        }
//        if(logContent != null) {
//            //printLoggingContent(point, logContent, time);
//            if(logHandlerActor == null){
//                logHandlerActor = actorSystem.actorOf(springExtension.props("logHandlerActor"), "logHandlerActor");
//            }
//            logHandlerActor.tell(logContent, ActorRef.noSender());
//        }
//
//        logger.debug("Finish normal advice ...");
//        return result;
//    }
//
//    @AfterThrowing(pointcut = "functionLoggingAspect()", throwing = "e")
//    public void exceptionAdvice(JoinPoint point, Throwable e) {
//        logger.debug("Start exception advice ...");
//        Quartet<String, String, String, Object> logContent = null;
//        try {
//            logContent = getFunctionLoggingContent(point);
//        } catch (ClassNotFoundException e1) {
//            logger.error("Error occurred while logging throwing advice", e1);
//        }
//        if(logContent != null) {
//            //printLoggingContent(point, logContent, 0L);
//            if(logHandlerActor == null){
//                logHandlerActor = actorSystem.actorOf(springExtension.props("logHandlerActor"), "logHandlerActor");
//            }
//            logHandlerActor.tell(logContent, ActorRef.noSender());
//        }
//        logger.debug("Finish exception advice ...");
//    }
//
//    public Quartet<String, String, String, Object> getFunctionLoggingContent(JoinPoint point) throws ClassNotFoundException {
//        Class target = Class.forName(point.getTarget().getClass().getName());
//        Method method = Arrays.stream(target.getMethods()).filter(x -> x.getName().equals(point.getSignature().getName())).findFirst().orElse(null);
//        return method == null ? null : new Quartet<>(method.getAnnotation(FunctionLog.class).module(),
//                method.getAnnotation(FunctionLog.class).operation(),
//                method.getAnnotation(FunctionLog.class).extension(),
//                point.getArgs());
//    }
//
//    public void printLoggingContent(JoinPoint point, Quartet loggingContent, Long duration){
//        String content = String.format("ClassName: %s, method: %s, time: %s Millis, module: %s, operation: %s, extends: %s, json: %s",
//                point.getTarget().getClass().getName(),
//                point.getSignature().getName(),
//                duration,
//                loggingContent.getValue0(),
//                loggingContent.getValue1(),
//                loggingContent.getValue2(),
//                JSONObject.toJSONString(loggingContent.getValue3()));
//        logger.info(content);
//    }
//
//
//
//    @Pointcut("@annotation(com.rexen.rest.annotation.FunctionLog)")
//    public void functionLoggingAspect() {
//    }
//}
//
