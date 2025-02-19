package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.HoaDonChiTiet;
import com.canhtv05.asm_java5.entity.HoaDonChiTietId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, HoaDonChiTietId> {

    List<HoaDonChiTiet> findAllByHoaDon_Id(Integer hoaDonId);
}