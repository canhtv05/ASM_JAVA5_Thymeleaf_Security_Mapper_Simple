package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.SanPhamCreationRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamUpdateRequest;
import com.canhtv05.asm_java5.dto.response.SanPhamResponse;
import com.canhtv05.asm_java5.entity.SanPham;
import com.canhtv05.asm_java5.mapper.SanPhamMapper;
import com.canhtv05.asm_java5.repository.SanPhamRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamService {

    SanPhamRepository sanPhamRepository;
    SanPhamMapper sanPhamMapper;

    public List<SanPhamResponse> findAll() {
        return sanPhamRepository.findAll().stream()
                .map(sanPhamMapper::toSanPhamSResponse)
                .toList();
    }

    public SanPhamResponse findById(Integer id) {
        return sanPhamMapper.toSanPhamSResponse(sanPhamRepository.findById(id).orElse(null));
    }

    public void deleteById(Integer id) {
        sanPhamRepository.deleteById(id);
    }

    public void add(SanPhamCreationRequest request) {
        SanPham sanPham = sanPhamMapper.toSanPham(request);

        if (sanPhamRepository.existsByMa(request.getMa())) {
            throw new DuplicateKeyException("Duplicate ma: " + request.getMa());
        }

        sanPhamRepository.save(sanPham);
    }

    public void update(SanPhamUpdateRequest request)  {
        SanPham sanPham = sanPhamMapper.toSanPhamUpdate(request);

        sanPhamRepository.save(sanPham);
    }
}
