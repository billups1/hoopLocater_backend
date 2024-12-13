package com.my.HoopLocater.infrastructure.persistence.like;


import com.my.HoopLocater.domain.hoop.Hoop;
import com.my.HoopLocater.domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {

    Like findByHoopAndWriter(Hoop hoop, String writer);

}