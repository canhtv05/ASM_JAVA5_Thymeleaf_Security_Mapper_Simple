package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.ChiTietSPCreationRequest;
import com.canhtv05.asm_java5.dto.request.ChiTietSPUpdateRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamCreationRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamUpdateRequest;
import com.canhtv05.asm_java5.dto.response.ChiTietSPResponse;
import com.canhtv05.asm_java5.dto.response.ChiTietSPResponse;
import com.canhtv05.asm_java5.entity.ChiTietSP;
import com.canhtv05.asm_java5.entity.SanPham;
import com.canhtv05.asm_java5.mapper.ChiTietSPMapper;
import com.canhtv05.asm_java5.repository.ChiTietSPRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChiTietSPService {

    ChiTietSPRepository chiTietSPRepository;
    ChiTietSPMapper chiTietSPMapper;

    public List<ChiTietSPResponse> findAll() {
        var list = chiTietSPRepository.findAll();

        return list.stream()
                .map(chiTietSPMapper::toChiTietSPResponse)
                .toList();
    }

    public ChiTietSPResponse findById(Integer id) {
        return chiTietSPMapper.toChiTietSPResponse(chiTietSPRepository.findById(id).orElse(null));
    }

    public void deleteById(Integer id) {
        chiTietSPRepository.deleteById(id);
    }

    public void add(ChiTietSPCreationRequest request) {
        ChiTietSP chiTietSP = chiTietSPMapper.toChiTietSP(request);

        chiTietSPRepository.save(chiTietSP);
    }

    public void update(ChiTietSPUpdateRequest request)  {
        ChiTietSP chiTietSP = chiTietSPMapper.toChiTietSPUpdate(request);

        chiTietSPRepository.save(chiTietSP);
    }
}
