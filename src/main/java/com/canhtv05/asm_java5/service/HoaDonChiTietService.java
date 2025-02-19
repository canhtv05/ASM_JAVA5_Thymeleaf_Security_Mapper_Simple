package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.HoaDonChiTietCreationRequest;
import com.canhtv05.asm_java5.entity.*;
import com.canhtv05.asm_java5.mapper.HoaDonMapper;
import com.canhtv05.asm_java5.mapper.KhachHangMapper;
import com.canhtv05.asm_java5.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonChiTietService {

    HoaDonRepository hoaDonRepository;
    ChiTietSPRepository chiTietSPRepository;
    HoaDonChiTietRepository hoaDonChiTietRepository;
    NhanVienService nhanVienService;
    KhachHangRepository khachHangRepository;

    public List<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId) {
        return hoaDonChiTietRepository.findAllByHoaDon_Id(hoaDonId);
    }

    public void add(HoaDonChiTietCreationRequest request, String taiKhoan) {
        HoaDon hoaDon = new HoaDon();

        String lastMa = hoaDonRepository.findTopByOrderByIdDesc()
                        .map(hd -> "HD" + (Integer.parseInt(hd.getMa().substring(2)) + 1))
                .orElse("HD1");

        NhanVien nhanVien = nhanVienService.findByTaiKhoan(taiKhoan);
        KhachHang khachHang = khachHangRepository.findById(request.getIdKh()).orElse(null);

        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setMa(lastMa);
        hoaDon.setTinhTrang(1);
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setKhachHang(khachHang);
        HoaDon hoaDonCreation = hoaDonRepository.save(hoaDon);

        for (int index = 0; index < request.getIdCTSP().size(); index++) {
            Integer idCTSP = request.getIdCTSP().get(index);
            Integer soLuong = request.getSoLuong().get(index);

            ChiTietSP chiTietSP = chiTietSPRepository.findById(idCTSP)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

            HoaDonChiTietId hoaDonChiTietId = new HoaDonChiTietId(hoaDonCreation.getId(), chiTietSP.getId());

            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .id(hoaDonChiTietId)
                    .soLuong(soLuong)
                    .donGia(chiTietSP.getGiaBan())
                    .chiTietSP(chiTietSP)
                    .hoaDon(hoaDonCreation)
                    .build();
            hoaDonChiTietRepository.save(hoaDonChiTiet);

            chiTietSP.setSoLuongTon(chiTietSP.getSoLuongTon() - soLuong);
            chiTietSPRepository.save(chiTietSP);
        }
    }
}
