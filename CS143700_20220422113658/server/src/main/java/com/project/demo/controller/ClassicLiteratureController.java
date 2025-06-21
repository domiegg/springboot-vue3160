package com.project.demo.controller;

import com.project.demo.entity.ClassicLiterature;
import com.project.demo.service.ClassicLiteratureService;
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
 *经典文献：(ClassicLiterature)表控制层
 *
 */
@RestController
@RequestMapping("/classic_literature")
public class ClassicLiteratureController extends BaseController<ClassicLiterature,ClassicLiteratureService> {

    /**
     *经典文献对象
     */
    @Autowired
    public ClassicLiteratureController(ClassicLiteratureService service) {
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
