package com.bc.wd.config;

import com.bc.wd.utils.CodeMsg;
import com.bc.wd.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理器的设置
 *
 * @author whl
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler2 {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("相关的异常信息：" + e.getMessage(), e);
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCm());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.error(CodeMsg.REQUEST_METHOD_NOT_SUPPORT);
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            return Result.error(CodeMsg.MEDIA_NOT_SUPPORT);
        } else if (e instanceof HttpMessageNotReadableException) {
            return Result.error(CodeMsg.PARAM_NOT_READ);
        } else if (e instanceof MissingServletRequestParameterException) {
            return Result.error(CodeMsg.PARAM_MISS);
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            BindingResult result = ex.getBindingResult();
            FieldError error = result.getFieldError();
            String field = error.getField();
            String code = error.getDefaultMessage();
            String message = String.format("%s:%s", field, code);
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
        } else if (e instanceof ServletRequestBindingException) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(e.getMessage()));
        } else if (e instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            ConstraintViolation<?> contraints = (ConstraintViolation<?>) constraintViolations.toArray()[0];
            log.info(contraints.getRootBeanClass().getSimpleName() +
                    "." + contraints.getPropertyPath() + " " + contraints.getMessage());
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(contraints.getMessage()));
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            log.error(ex.getName() + ":" + ex.getValue() + "," + ex.getMessage());
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(ex.getMessage()));
        } else if (e instanceof NullPointerException) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(e.getMessage()));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
