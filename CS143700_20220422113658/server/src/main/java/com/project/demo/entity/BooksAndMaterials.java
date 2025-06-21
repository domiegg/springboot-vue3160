package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *图书资料：(BooksAndMaterials)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "BooksAndMaterials")
public class BooksAndMaterials implements Serializable {

    //BooksAndMaterials编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "books_and_materials_id")
    private Integer books_and_materials_id;
    // 图书名称
    @Basic
    private String book_name;
    // 封面
    @Basic
    private String cover;
    // 作者
    @Basic
    private String author;
    // 出版社
    @Basic
    private String press;
    // 出版时间
    @Basic
    private Timestamp publication_time;
    // 内容简介
    @Basic
    private String content_validity;
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
