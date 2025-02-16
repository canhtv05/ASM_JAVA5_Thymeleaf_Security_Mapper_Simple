package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.KhachHangCreationRequest;
import com.canhtv05.asm_java5.dto.request.KhachHangUpdateRequest;
import com.canhtv05.asm_java5.dto.response.KhachHangResponse;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.mapper.KhachHangMapper;
import com.canhtv05.asm_java5.repository.KhachHangRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhachHangService {

    KhachHangRepository khachHangRepository;
    KhachHangMapper khachHangMapper;

    public List<KhachHangResponse> findAll() {
        return khachHangRepository.findAll().stream()
                .map(khachHangMapper::toKhachHangSResponse)
                .toList();
    }

    public KhachHangResponse findById(Integer id) {
        return khachHangMapper.toKhachHangSResponse(khachHangRepository.findById(id).orElse(null));
    }

    public void deleteById(Integer id) {
        khachHangRepository.deleteById(id);
    }

    public void add(KhachHangCreationRequest request) {
        KhachHang khachHang = khachHangMapper.toKhachHang(request);

        if (khachHangRepository.existsByMa(request.getMa())) {
            throw new DuplicateKeyException("Duplicate ma: " + request.getMa());
        }

        khachHangRepository.save(khachHang);
    }

    public void update(KhachHangUpdateRequest request)  {
        KhachHang khachHang = khachHangMapper.toKhachHangUpdate(request);

        khachHangRepository.save(khachHang);
    }
}
