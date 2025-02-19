package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.dto.response.GioHangResponse;
import com.canhtv05.asm_java5.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    List<GioHang> findAllByKhachHang_Id(Integer khachHangId);
}