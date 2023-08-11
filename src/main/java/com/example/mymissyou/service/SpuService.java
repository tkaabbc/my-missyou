package com.example.mymissyou.service;

import com.example.mymissyou.model.Spu;
import com.example.mymissyou.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuService {
    @Autowired
    private SpuRepository spuRepository;

    public Spu getSpu(Long id) {
        return this.spuRepository.findOneById(id);
    }
    public List<Spu> getAll() {
        return this.spuRepository.findAll();
    }
}
