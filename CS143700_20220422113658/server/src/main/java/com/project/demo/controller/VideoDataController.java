package com.project.demo.controller;

import com.project.demo.entity.VideoData;
import com.project.demo.service.VideoDataService;
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
 *视频资料：(VideoData)表控制层
 *
 */
@RestController
@RequestMapping("/video_data")
public class VideoDataController extends BaseController<VideoData,VideoDataService> {

    /**
     *视频资料对象
     */
    @Autowired
    public VideoDataController(VideoDataService service) {
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
