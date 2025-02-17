package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.NhanVien;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    Optional<NhanVien> findByMa(@Size(max = 20) String ma);

    @Query(value = "SELECT COUNT(*) FROM dbo.NhanVien nv ",nativeQuery = true)
    long count();

    Optional<NhanVien> findTopByOrderByIdDesc();

    Optional<NhanVien> findByTaiKhoan(String taiKhoan);

    boolean existsByTaiKhoan(String taiKhoan);
}