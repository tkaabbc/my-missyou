package com.example.mymissyou.model;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
