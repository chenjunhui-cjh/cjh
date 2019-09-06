package com.rexen.rest.app.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Autowired
    private ProcessEngine processEngine;

    /**
     * .提交采购订单的审批请求
     *
     */
    @PostMapping("/start")
    public R startFlow() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("faqizhe", 11);
        map.put("applyDays", 3);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("ceshiProcessModelKey", map);
        Authentication.setAuthenticatedUserId("aabbcc");
        String processId = processInstance.getId();
        String name = processInstance.getName();
        System.out.println(processId + ":" + name);
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().list();
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group: " + task.getName());
        }
        return R.ok(processId + ":" + name);
    }

    @GetMapping("/taskList")
    public void getTasks(){
        List<ProcessInstance> plist =runtimeService.createProcessInstanceQuery().processDefinitionKey("ceshiProcessModelKey").list();
        ProcessInstance processInstance = plist.get(0);
        List<Task> tasks1 = taskService.createTaskQuery().list();
        System.out.println(tasks1);
        List<Task> tasks2 =taskService.createTaskQuery().taskAssignee("fenpeiren").list();
        System.out.println(tasks2);
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("houxuanGroups").processInstanceId(processInstance.getId()).list();
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group: " + task.getName());
        }
    }

    @GetMapping("/claimTask")
    public void claimTask(){
        List<Task> tasks11 = taskService.createTaskQuery().processDefinitionId("ceshiProcessModelKey").list();
        System.out.println(tasks11);
        List<Task> tasks12 = taskService.createTaskQuery().processDefinitionId("ceshiProcessModelKey:1:68a9ffce-cd2b-11e9-94a5-1c1b0d35e983").list();
        System.out.println(tasks12);
        List<Task> tasks13 = taskService.createTaskQuery().processInstanceId("ca2be5a3-cd9b-11e9-9d61-02004c4f4f50").list();
        System.out.println(tasks13);
        List<Task> tasks14 =taskService.createTaskQuery().processDefinitionKey("ceshiProcessModelKey").list();
        System.out.println(tasks14);
        List<Task> tasks1 = taskService.createTaskQuery().list();
        System.out.println(tasks1);
        String taskId = tasks1.get(0).getId();
        List<Task> tasks2 =taskService.createTaskQuery().taskAssignee("xiaoming").list();
        System.out.println(tasks2);
        Task task3 = taskService.createTaskQuery().taskAssignee("xiaoming").singleResult();
        System.out.println(task3);
        taskService.complete(taskId);
    }
}
