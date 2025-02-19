package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.response.GioHangResponse;
import com.canhtv05.asm_java5.mapper.GioHangMapper;
import com.canhtv05.asm_java5.repository.GioHangRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GioHangService {

    GioHangRepository gioHangRepository;
    GioHangMapper giohangMapper;

    public List<GioHangResponse> findAllByKhachHang_Id(Integer khachHangId) {
        return gioHangRepository.findAllByKhachHang_Id(khachHangId).stream()
                .map(giohangMapper::toGioHangResponse)
                .toList();
    }

//    public SanPhamResponse findById(Integer id) {
//        return sanPhamMapper.toSanPhamSResponse(sanPhamRepository.findById(id).orElse(null));
//    }
//
//    public void deleteById(Integer id) {
//        sanPhamRepository.deleteById(id);
//    }
//
//    public void add(SanPhamCreationRequest request) {
//        SanPham sanPham = sanPhamMapper.toSanPham(request);
//
//        if (sanPhamRepository.existsByMa(request.getMa())) {
//            throw new DuplicateKeyException("Duplicate ma: " + request.getMa());
//        }
//
//        sanPhamRepository.save(sanPham);
//    }
//
//    public void update(SanPhamUpdateRequest request)  {
//        SanPham sanPham = sanPhamMapper.toSanPhamUpdate(request);
//
//        sanPhamRepository.save(sanPham);
//    }
}
