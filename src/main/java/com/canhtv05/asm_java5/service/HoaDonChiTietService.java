package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.HoaDonChiTietCreationRequest;
import com.canhtv05.asm_java5.dto.request.HoaDonCreationRequest;
import com.canhtv05.asm_java5.dto.response.GioHangResponse;
import com.canhtv05.asm_java5.entity.*;
import com.canhtv05.asm_java5.mapper.GioHangMapper;
import com.canhtv05.asm_java5.mapper.HoaDonMapper;
import com.canhtv05.asm_java5.repository.ChiTietSPRepository;
import com.canhtv05.asm_java5.repository.GioHangRepository;
import com.canhtv05.asm_java5.repository.HoaDonChiTietRepository;
import com.canhtv05.asm_java5.repository.HoaDonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonService {

    HoaDonRepository hoaDonRepository;
    HoaDonMapper hoaDonMapper;
    ChiTietSPRepository chiTietSPRepository;
    HoaDonChiTietRepository hoaDonChiTietRepository;
//
//    public List<GioHangResponse> findAllByKhachHang_Id(Integer khachHangId) {
//        return gioHangRepository.findAllByKhachHang_Id(khachHangId).stream()
//                .map(giohangMapper::toGioHangResponse)
//                .toList();
//    }

//    public SanPhamResponse findById(Integer id) {
//        return sanPhamMapper.toSanPhamSResponse(sanPhamRepository.findById(id).orElse(null));
//    }
//
//    public void deleteById(Integer id) {
//        sanPhamRepository.deleteById(id);
//    }
//
    public void add(HoaDonChiTietCreationRequest request) {
        HoaDon hoaDon = hoaDonMapper.toHoaDon(request.getHoaDonCreationRequest());

        String lastMa = hoaDonRepository.findTopByOrderByIdDesc()
                        .map(hd -> "HD" + (Integer.parseInt(hd.getMa().substring(2)) + 1))
                .orElse("HD1");

        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setMa(lastMa);
        hoaDon.setTinhTrang(1);
        hoaDon.setNhanVien(request.getHoaDonCreationRequest().getNhanVien());
        hoaDon.setKhachHang(request.getHoaDonCreationRequest().getKhachHang());
        HoaDon hoaDonCreation = hoaDonRepository.save(hoaDon);

        for (Integer i : request.getIdCTSP()) {
            ChiTietSP chiTietSP = chiTietSPRepository.findById(i).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

            HoaDonChiTietId hoaDonChiTietId = new HoaDonChiTietId(hoaDonCreation.getId(), chiTietSP.getId());
            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .id(hoaDonChiTietId)
                    .soLuong(request.getSoLuong())
                    .donGia(request.getDonGia())
                    .build();
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        }
    }
//
//    public void update(SanPhamUpdateRequest request)  {
//        SanPham sanPham = sanPhamMapper.toSanPhamUpdate(request);
//
//        sanPhamRepository.save(sanPham);
//    }
}
