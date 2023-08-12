package com.example.mymissyou.service;

import com.example.mymissyou.model.Spu;
import com.example.mymissyou.repository.SpuRepository;
import com.example.mymissyou.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size ) {
        Pageable page = PageRequest.of(pageNum, size, Sort.by("createTime"));
        return this.spuRepository.findAll(page);
    }
}
