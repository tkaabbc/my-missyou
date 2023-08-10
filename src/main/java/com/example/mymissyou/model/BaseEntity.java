package com.example.mymissyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
}
