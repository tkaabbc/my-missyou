package com.example.mymissyou.repository;

import com.example.mymissyou.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpuRepository extends JpaRepository<Spu, Long> {
    Spu findOneById(Long id);

    Page<Spu> findByCategoryIdOrderByCreateTimeDesc(Long cid, Pageable pageable);

    List<Spu> findByIdIn(List<Long> ids);

    Page<Spu> findByRootCategoryIdOrderByCreateTime(Long cid, Pageable pageable);

    Page<Spu> findByTitleLikeOrSubtitleLike(String keyword, String keyword1, Pageable pageable);
}
