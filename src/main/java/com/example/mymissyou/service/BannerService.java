package com.example.mymissyou.service;

import com.example.mymissyou.model.Banner;
import com.example.mymissyou.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepository;
    public Banner getByName(String name) {
        return bannerRepository.findOneByName(name);
    }
}
