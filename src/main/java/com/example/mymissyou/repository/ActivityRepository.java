package com.example.mymissyou.repository;

import com.example.mymissyou.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findByName(String name);

    Optional<Activity> findByCouponListId(Long couponId);
}
