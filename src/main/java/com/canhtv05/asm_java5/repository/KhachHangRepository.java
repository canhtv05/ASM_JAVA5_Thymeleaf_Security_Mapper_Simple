package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.KhachHang;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    boolean existsByMa(@Size(max = 20) String ma);

    Optional<KhachHang> findTopByOrderByIdDesc();
}