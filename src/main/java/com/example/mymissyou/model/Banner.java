package com.example.mymissyou.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Banner extends BaseEntity{
    @Id
    private Long id;
    private String name;
    private String description;
    private String title;
    private String img;

    @OneToMany
    @JoinColumn(name="bannerId")
    private List<BannerItem> items;
}
