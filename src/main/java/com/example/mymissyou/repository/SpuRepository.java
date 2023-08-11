package com.example.mymissyou.repository;

import com.example.mymissyou.model.Spu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpuRepository extends JpaRepository<Spu, Long> {
    Spu findOneById(Long id);
}
