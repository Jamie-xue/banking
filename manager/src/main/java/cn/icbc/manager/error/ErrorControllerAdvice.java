package cn.icbc.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @Auther: asus
 * @Date: 2018/8/25 15:34
 */
@ControllerAdvice(basePackages = {"cn.icbc.manager.service","cn.icbc.manager.controller"})
public class ErrorControllerAdvice {


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){

        HashMap<String, Object> attrs = new HashMap<>();
        String code = e.getMessage();
        ErrorEnum errorEnum = ErrorEnum.getByCode(code);
        attrs.put("code",errorEnum.getCode());
        attrs.put("message",errorEnum.getMessage());
        attrs.put("canRetry",errorEnum.isCanRetry());
        attrs.put("type","advice");
        //Assert.isNull(attrs,"advice空");
        return  new ResponseEntity(attrs, HttpStatus.INTERNAL_SERVER_ERROR);
    }










}
