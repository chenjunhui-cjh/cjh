package com.rexen.rest.common.exception;

/**
 * Description：
 *
 * @author GavinHacker
 * @since Created in 下午5:12 2019/5/13
 */
public class WorkflowDefinitionNotFoundException extends RestBaseException {

    public WorkflowDefinitionNotFoundException(String message) {
        super(message);
    }

    public WorkflowDefinitionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowDefinitionNotFoundException(Throwable cause) {
        super(cause);
    }

    public WorkflowDefinitionNotFoundException(String message, String code) {
        super(message, code);
    }
}
