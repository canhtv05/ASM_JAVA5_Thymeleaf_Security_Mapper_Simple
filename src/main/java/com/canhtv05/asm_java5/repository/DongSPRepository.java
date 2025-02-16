package com.canhtv05.asm_java5.repository;

import com.canhtv05.asm_java5.entity.DongSP;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DongSPRepository extends JpaRepository<DongSP, Integer> {
    boolean existsByMa(@Size(max = 20) String ma);
}