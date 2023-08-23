package com.example.mymissyou.service;

import com.example.mymissyou.model.Sku;
import com.example.mymissyou.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuService {

    @Autowired
    private SkuRepository skuRepository;

    public List<Sku> getSkuListByIds(List<Long> ids) {
        return this.skuRepository.findAllByIdIn(ids);
    }
}
