package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.HoaDonChiTiet;
import com.canhtv05.asm_java5.entity.HoaDonChiTietId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, HoaDonChiTietId> {
}