//package com.rexen.rest.app.advice;
//
//import com.rexen.rest.app.wrap.ServiceStatus;
//import com.rexen.rest.common.util.UUIDGenerateUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.log4j.Logger;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * @author: GavinHacker
// * @description:
// * @date: Created in 下午3:27 18/3/1
// * @modifiedBy:
// */
//@RestControllerAdvice
//public class ExceptionHandlerAdvice {
//
//    Logger logger = Logger.getLogger(ExceptionHandlerAdvice.class);
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        /**Exception handler initialized*/
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    public ServiceStatus errorHandler(Exception ex) {
//        String traceId = UUIDGenerateUtils.generateWithoutLineUUID();
//        if(ex != null) {
//            logger.warn("ExceptionHandler trace: {".concat(traceId).concat("} => ").concat(ExceptionUtils.getStackTrace(ex)));
//        }
//        return new ServiceStatus(ServiceStatus.Status.fail, "服务异常, 请查看服务端日志. [Trace-ID]: {" + traceId + "}", ExceptionUtils.getMessage(ex));
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ServiceStatus handleValidationException(MethodArgumentNotValidException ex) {
//        if(ex != null) {
//            logger.error("参数验证失败".concat(ex.getMessage()));
//        }
//        return new ServiceStatus(ServiceStatus.Status.fail, "参数验证失败", StringUtils.EMPTY, ex.getMessage().substring(ex.getMessage().lastIndexOf(":")));
//    }
//}
