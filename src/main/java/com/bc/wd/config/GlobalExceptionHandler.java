//package com.bc.wd.config;
//
//import com.bc.wd.utils.BaseResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @program: whl-project
// * @description:
// * @author: Mr.Wang
// * @create: 2020-04-22 14:49
// **/
//@Slf4j
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ResponseBody
//    @ExceptionHandler(NullPointerException.class)
//    public BaseResult globalException(HttpServletResponse response, Exception ex) {
//        BaseResult result = new BaseResult(BaseResult.RESULT_FAIL, "request error:" + response.getStatus(), ex.getMessage());
//        return result;
//    }
//}
