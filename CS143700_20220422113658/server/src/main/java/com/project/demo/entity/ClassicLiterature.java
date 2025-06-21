package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *经典文献：(ClassicLiterature)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ClassicLiterature")
public class ClassicLiterature implements Serializable {

    //ClassicLiterature编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classic_literature_id")
    private Integer classic_literature_id;
    // 文献名称
    @Basic
    private String document_name;
    // 作者
    @Basic
    private String author;
    // 发表日期
    @Basic
    private Timestamp publication_date;
    // 发表地方
    @Basic
    private String place_of_publication;
    // 文献
    @Basic
    private String literature;
    // 详情
    @Basic
    private String details;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
