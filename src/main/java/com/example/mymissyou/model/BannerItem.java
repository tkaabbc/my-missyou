package com.example.mymissyou.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BannerItem extends BaseEntity {
    @Id
    private Long id;
    private String img;
    private String keyword;
    private Short type;
    private Long bannerId;
    private String name;
}
