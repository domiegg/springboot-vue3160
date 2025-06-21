package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *视频资料：(VideoData)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VideoData")
public class VideoData implements Serializable {

    //VideoData编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_data_id")
    private Integer video_data_id;
    // 视频名称
    @Basic
    private String video_name;
    // 封面
    @Basic
    private String cover;
    // 视频
    @Basic
    private String video;
    // 作者
    @Basic
    private String author;
    // 发布时间
    @Basic
    private Timestamp release_time;
    // 视频介绍
    @Basic
    private String video_introduction;
    // 详情
    @Basic
    private String details;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
