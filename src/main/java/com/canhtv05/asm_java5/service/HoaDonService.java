package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.entity.HoaDon;
import com.canhtv05.asm_java5.repository.HoaDonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonService {

    HoaDonRepository hoaDonRepository;

    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    public HoaDon findById(int id) {
        return hoaDonRepository.findById(id).orElse(null);
    }
}
