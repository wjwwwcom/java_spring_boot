package com.hqyj.JavaSpringBoot.modules.common.controller;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result<String> handle403(){
        return new Result<>(Result.ResultStatus.FAILD.status,"","/common/403");
    }
}
