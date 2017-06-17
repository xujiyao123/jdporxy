package com.xujiyao.jdporxy.controller;

import com.xujiyao.jdporxy.eniity.JDEntity;
import com.xujiyao.jdporxy.service.JDProxyService;
import com.xujiyao.jdporxy.util.JsonResult;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 42224 on 2017/6/17.
 */
@Controller
@RequestMapping("proxy")
public class ProxyController extends  ExceptionController {


    public static final org.apache.commons.logging.Log logger = LogFactory.getLog(ProxyController.class);

    @Resource
    private JDProxyService jdProxyService;

    @ResponseBody
    @RequestMapping("/do")
    public Object getProxy(@RequestParam(value = "keyword") String keyword) throws Exception {

        List<JDEntity> allbooks = new ArrayList<JDEntity>();
        List<JDEntity> books;

        Long a = System.currentTimeMillis();
        Integer page = 1;
        do {
          books  = (List<JDEntity>) jdProxyService.doPorxy(keyword , page);
          page++;

          allbooks.addAll(books);
        }while (page != 20);

        Long b = System.currentTimeMillis();

        logger.info("用时" + "" + (b-a) + "ms 共" + allbooks.size() + "条数据"  );

        return new  JsonResult<List<JDEntity>>(allbooks);

    }


}
