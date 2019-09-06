package com.rexen.rest.service;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    public void test(){
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .latestVersion()
                .singleResult();
        Map<String, Object> variables = new HashMap<>();
//        variables.put("delegateExpression", new ApproveTaskAssignmentListener());
//        variables.put("expression", new ApproveTaskDeleteListener());
        //发起流程
        processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId(),variables);

    }


}
