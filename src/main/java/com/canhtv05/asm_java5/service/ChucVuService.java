package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.constant.PredefinedRole;
import com.canhtv05.asm_java5.dto.response.ChucVuResponse;
import com.canhtv05.asm_java5.entity.ChucVu;
import com.canhtv05.asm_java5.mapper.ChucVuMapper;
import com.canhtv05.asm_java5.repository.ChucVuRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChucVuService {

    ChucVuRepository chucVuRepository;
    ChucVuMapper chucVuMapper;

    public List<ChucVuResponse> findAll() {
        var list = chucVuRepository.findAll();

        return list.stream()
                .map(chucVuMapper::toChucVuResponse)
                .toList();
    }

    public ChucVuResponse findByMa(String ma) {
        return chucVuMapper.toChucVuResponse(chucVuRepository.findByMa(ma)
                .orElseThrow(() -> new NullPointerException("NOT FOUND Ma ChucVu: " + ma)));
    }
}
