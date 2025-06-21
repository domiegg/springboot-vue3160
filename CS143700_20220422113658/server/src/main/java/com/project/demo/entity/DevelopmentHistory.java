package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *发展历程：(DevelopmentHistory)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DevelopmentHistory")
public class DevelopmentHistory implements Serializable {

    //DevelopmentHistory编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "development_history_id")
    private Integer development_history_id;
    // 标题
    @Basic
    private String title;
    // 封面
    @Basic
    private String cover;
    // 年份
    @Basic
    private String particular_year;
    // 地点
    @Basic
    private String place;
    // 主要人物
    @Basic
    private String dominating_figure;
    // 起因
    @Basic
    private String cause;
    // 发展意义
    @Basic
    private String development_significance;
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
