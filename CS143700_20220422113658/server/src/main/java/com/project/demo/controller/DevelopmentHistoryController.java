package com.project.demo.controller;

import com.project.demo.entity.DevelopmentHistory;
import com.project.demo.service.DevelopmentHistoryService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *发展历程：(DevelopmentHistory)表控制层
 *
 */
@RestController
@RequestMapping("/development_history")
public class DevelopmentHistoryController extends BaseController<DevelopmentHistory,DevelopmentHistoryService> {

    /**
     *发展历程对象
     */
    @Autowired
    public DevelopmentHistoryController(DevelopmentHistoryService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
