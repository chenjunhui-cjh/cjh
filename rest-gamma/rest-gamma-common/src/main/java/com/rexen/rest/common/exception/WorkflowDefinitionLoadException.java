package com.rexen.rest.common.exception;

/**
 * Description：
 *
 * @author GavinHacker
 * @since Created in 下午8:23 2019/5/15
 */
public class WorkflowDefinitionLoadException extends RestBaseException {

    public WorkflowDefinitionLoadException(String message) {
        super(message);
    }

    public WorkflowDefinitionLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowDefinitionLoadException(Throwable cause) {
        super(cause);
    }

    public WorkflowDefinitionLoadException(String message, String code) {
        super(message, code);
    }
}
