package cn.qingyumao.scaffold.web;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fz51
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {


    private MessageSource messageSource;


   /* @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public WebResp aggregateDataExpirationException() {
        // 参数缺失错误
        String msgCode = "exception_msg.data_expiration";
        WebResp result = WebResp.buildFailure(msgCode, messageSource.getMessage(msgCode, null, LocaleContextHolder.getLocale()));
        result.setErrorShowType(4);
        return result;
    }*/

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResp<Object> handNotParamException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        // log.error(e.getMessage());
        log.error(ExceptionUtil.getRootCauseMessage(e));
        // 参数缺失错误
        String msgCode = "exception_msg.request_param_missing";
        WebResp<Object> result = WebResp.buildFailure(msgCode, messageSource.getMessage(msgCode, null, LocaleContextHolder.getLocale()));
        result.setErrorShowType(1);
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResp<Object> handNotParamException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        // 参数验证错误 validate
        List<String> errors = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        WebResp result = WebResp.buildFailure("PARAM_VALIDATE_ERROR", String.join(",", errors));
        result.setDebugMessage("参数异常：" + e.getMessage());
        result.setErrorShowType(1);
        return result;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public WebResp<Object> handIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        // 参数验证错误 validate
        WebResp result = WebResp.buildFailure("PARAM_VALIDATE_ERROR", e.getMessage());
        result.setDebugMessage("参数异常：" + e.getMessage());
        result.setErrorShowType(1);
        return result;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WebResp<Object> handleGlobalException(Exception e) {
        e.printStackTrace();
        if (e instanceof HttpMessageNotReadableException) {
            WebResp result = WebResp.buildFailure("PARAM_ERROR", e.getMessage());
            return result;
        }
        WebResp result = WebResp.buildFailure("UNKNOWN_ERROR", "未知错误");
        return result;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
