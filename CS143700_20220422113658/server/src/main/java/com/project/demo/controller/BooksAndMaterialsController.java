package com.project.demo.controller;

import com.project.demo.entity.BooksAndMaterials;
import com.project.demo.service.BooksAndMaterialsService;
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
 *图书资料：(BooksAndMaterials)表控制层
 *
 */
@RestController
@RequestMapping("/books_and_materials")
public class BooksAndMaterialsController extends BaseController<BooksAndMaterials,BooksAndMaterialsService> {

    /**
     *图书资料对象
     */
    @Autowired
    public BooksAndMaterialsController(BooksAndMaterialsService service) {
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
