package com.rexen.rest.app.controller;

import org.checkerframework.checker.units.qual.A;
import org.flowable.engine.*;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/holiday")
public class HolidayApproveController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/approve")
    public void approveStart() {
        runtimeService.startProcessInstanceByKey("holiday");
        //获取最新的流程
        Task task = taskService.createTaskQuery().orderByTaskCreateTime().desc().list().get(0);
//      taskService.setVariablesLocal();  将变量存到task里
        //将变量存到流程里
        taskService.setVariable(task.getId(),"count",5);
        //完成任务
        taskService.complete(task.getId());
        //获取下个任务
        Task task2 = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        //输出已结束任务，下一个任务
        System.out.println(task);
        System.out.println(task2);
        //将变量存到下个任务里
        taskService.setAssignee(task2.getId(),"ccc");
        Map map = new HashMap<String,Object>(16);
        map.put("count",5);
        taskService.setVariablesLocal(task2.getId(),map);
        //输出流程id
        System.out.println(task.getProcessInstanceId());

    }

    @GetMapping(value = "/applyDay")
    public void applyDay() {

        Task task = taskService.createTaskQuery().processInstanceId("30074dc0-d040-11e9-a468-107b444d86da").singleResult();

        Object count = taskService.getVariables(task.getId()).get("count");
        System.out.println(count);

//      taskService.claim(task.getId(),"ccc");
        boolean isPass = true;
        Map map = new HashMap<String,Object>(16);
        map.put("isPass",isPass);
        taskService.complete(task.getId(),map);

        Task task2 = taskService.createTaskQuery().processInstanceId("30074dc0-d040-11e9-a468-107b444d86da").singleResult();
        System.out.println(task2);

    }

    @GetMapping(value = "/applyMonth")
    public void applyMonth() {

        Task task = taskService.createTaskQuery().processInstanceId("30074dc0-d040-11e9-a468-107b444d86da").singleResult();



    }

}
