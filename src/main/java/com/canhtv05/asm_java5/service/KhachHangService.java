package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.constant.PredefinedRole;
import com.canhtv05.asm_java5.dto.request.KhachHangCreationRequest;
import com.canhtv05.asm_java5.dto.request.KhachHangUpdateRequest;
import com.canhtv05.asm_java5.dto.response.KhachHangResponse;
import com.canhtv05.asm_java5.entity.ChucVu;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.mapper.ChucVuMapper;
import com.canhtv05.asm_java5.mapper.KhachHangMapper;
import com.canhtv05.asm_java5.repository.KhachHangRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhachHangService {

    KhachHangRepository khachHangRepository;
    KhachHangMapper khachHangMapper;
    ChucVuMapper chucVuMapper;
    ChucVuService chucVuService;
    PasswordEncoder passwordEncoder;

    public List<KhachHangResponse> findAll() {
        return khachHangRepository.findAll().stream()
                .map(khachHangMapper::toKhachHangSResponse)
                .toList();
    }

    public KhachHang findByTaiKhoan(String taiKhoan) {
        return khachHangRepository.findByTaiKhoan(taiKhoan).orElse(null);
    }

    public KhachHangResponse findById(Integer id) {
        return khachHangMapper.toKhachHangSResponse(khachHangRepository.findById(id).orElse(null));
    }

    public void deleteById(Integer id) {
        khachHangRepository.deleteById(id);
    }

    public void add(KhachHangCreationRequest request) {
        KhachHang khachHang = khachHangMapper.toKhachHang(request);

        if (khachHangRepository.existsByTaiKhoan(khachHang.getTaiKhoan())) {
            throw new DuplicateKeyException("TaiKhoan is already in use");
        }

        ChucVu chucVu = chucVuMapper.toChucVu(chucVuService.findByMa(PredefinedRole.CUSTOMER_ROLE));
        khachHang.setChucVu(chucVu);

        String lastMa = khachHangRepository.findTopByOrderByIdDesc()
                .map(nv -> "KH" + (Integer.parseInt(nv.getMa().substring(2)) + 1))
                .orElse("KH1");

        khachHang.setMa(lastMa);
        khachHang.setMatKhau(passwordEncoder.encode(request.getMatKhau()));

        khachHangRepository.save(khachHang);
    }

    public void update(KhachHangUpdateRequest request)  {
        KhachHang khachHang = khachHangMapper.toKhachHangUpdate(request);
        khachHang.setMatKhau(passwordEncoder.encode(request.getMatKhau()));

        khachHangRepository.save(khachHang);
    }
}
