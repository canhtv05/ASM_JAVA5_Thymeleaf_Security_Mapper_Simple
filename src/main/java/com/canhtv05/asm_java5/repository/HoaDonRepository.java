package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    Optional<HoaDon> findTopByOrderByIdDesc();
}