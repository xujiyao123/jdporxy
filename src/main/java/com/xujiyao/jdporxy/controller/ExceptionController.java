package com.xujiyao.jdporxy.controller;

import com.xujiyao.jdporxy.service.impl.JDProxyServiceImpl;
import com.xujiyao.jdporxy.util.JsonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xujiyao on 2017/4/15.
 * StudentService
 * ${PACKAGE_NAME}
 */
@Controller
public class ExceptionController {

    public static final Log logger = LogFactory.getLog(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult<Object> exceptionHandle(Exception e) {

        logger.error(e);

        e.printStackTrace();

        return new JsonResult<Object>(e);
    }
    
    


    @RequestMapping(value = "/error_404" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String error404(){
        return "{\"msg\":\"没有此页面\",\"code\":\"404\"}";
    }

//    @ExceptionHandler(value = { MissingServletRequestParameterException.class, ServletRequestBindingException.class,
//            TypeMismatchException.class, HttpMessageNotReadableException.class, BindException.class,
//            MethodArgumentNotValidException.class, MissingServletRequestPartException.class,
//             })
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public @ResponseBody JsonResult<Object> processBadRequest(Exception exception) {
//        return new JsonResult<Object>(exception);
//    }
//
//    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
//    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
//    public @ResponseBody JsonResult<Object> processMethodNotAllowed(Exception exception) {
//        return new JsonResult<Object>(exception);
//    }
//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public @ResponseBody JsonResult<Object> processInternalServerError(Exception exception) {
//        System.out.println("====="+exception.getMessage());
//        return new JsonResult<Object>(exception);
//    }


}
