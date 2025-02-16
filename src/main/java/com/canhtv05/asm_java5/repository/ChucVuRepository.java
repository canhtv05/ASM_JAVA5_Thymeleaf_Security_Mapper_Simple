package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.ChucVu;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {
    Optional<ChucVu> findByMa(@Size(max = 20) String ma);

    boolean existsByMa(@Size(max = 20) String ma);
}