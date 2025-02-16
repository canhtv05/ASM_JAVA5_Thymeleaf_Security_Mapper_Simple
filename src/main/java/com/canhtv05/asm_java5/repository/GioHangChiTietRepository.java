package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.GioHangChiTiet;
import com.canhtv05.asm_java5.entity.GioHangChiTietId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {
}