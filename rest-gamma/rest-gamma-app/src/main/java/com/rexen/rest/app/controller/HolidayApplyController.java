package com.rexen.rest.app.controller;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;

public class HolidayApplyController implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        final Map<String, Object> variables = delegateExecution.getVariables();
        System.out.println(variables);
    }
}
