package com.rexen.rest.app.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.alibaba.fastjson.JSONObject;
import com.rexen.rest.annotation.FunctionLog;
import com.rexen.rest.app.cache.RedisCommonCacheManager;
import com.rexen.rest.app.cache.SessionCacheManager;
import com.rexen.rest.app.config.akka.SpringExtension;
import com.rexen.rest.app.security.RestRealm;
import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author: GavinHacker
 * @description:
 * @date: Created in 下午5:21 18/5/18
 * @modifiedBy:
 */
@RestController
public class SampleController extends BaseController{

    Logger logger = Logger.getLogger(SampleController.class);

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    @Autowired
    private RedisCommonCacheManager<String, Object> redisCommonCacheManager;

    @FunctionLog(module = "样例模块", operation = "显示Hello World")
    @RequestMapping(value = "render", method = RequestMethod.GET)
    public String renderHelloWorld(){
        return JSONObject.toJSONString(new Pair<>("Hello", "World"));
    }

    @FunctionLog(module = "样例模块", operation = "测试session")
    @RequestMapping(value = "setAttr", method = RequestMethod.GET)
    public String setAttr(String key, String value){
        SessionCacheManager.setAttribute(key, value);
        redisCommonCacheManager.put(key, value);
        return JSONObject.toJSONString(new Pair<>("message", "success"));
    }

    @FunctionLog(module = "样例模块", operation = "测试session")
    @RequestMapping(value = "getAttr", method = RequestMethod.GET)
    public String getAttr(String key){
        return JSONObject.toJSONString(new Pair<>(SessionCacheManager.getAttribute(key), redisCommonCacheManager.get(key)));
    }

    @FunctionLog(module = "样例模块", operation = "显示Hello World")
    @RequestMapping(value = "a/b/render", method = RequestMethod.GET)
    public String renderHelloWorld1(){
        return JSONObject.toJSONString(new Pair<>("Hello", "Hacker"));
    }

    @FunctionLog(module = "测试Actor", operation = "测试Actor")
    @RequestMapping(value = "actor", method = RequestMethod.GET)
    public String renderActor(String isStart){
        CompletableFuture future = new CompletableFuture<>();
        CompletableFuture future2 = new CompletableFuture<>();

        if(Boolean.valueOf(isStart)){
            ActorRef processor = actorSystem.actorOf(springExtension.props("simpleProcessor"), "simple-processor");
            processor.tell("You can..", null);
            ActorRef producer = actorSystem.actorOf(springExtension.props("simpleProducer",future2),"simple-producer");
            producer.tell("Can I ?", processor);

            logger.info(processor.path().toString());
            logger.info(producer.path().toString());
            return JSONObject.toJSONString(new Pair<>("Hello", "Actor"));
        }

        ActorSelection actorSelectionProcessor = actorSystem.actorSelection("/user/simple-processor");
        actorSelectionProcessor.tell("tell selection processor.", null);
        ActorSelection actorSelectionProducer = actorSystem.actorSelection("/user/simple-producer");
        actorSelectionProducer.tell("tell selection producer", null);
        return JSONObject.toJSONString(new Pair<>("Hello", "Actor"));
    }
}
