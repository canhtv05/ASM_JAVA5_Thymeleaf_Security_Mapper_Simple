package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.constant.PredefinedRole;
import com.canhtv05.asm_java5.dto.request.NhanVienCreationRequest;
import com.canhtv05.asm_java5.entity.ChucVu;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.entity.NhanVien;
import com.canhtv05.asm_java5.mapper.NhanVienMapper;
import com.canhtv05.asm_java5.repository.ChucVuRepository;
import com.canhtv05.asm_java5.repository.NhanVienRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienService {

    NhanVienRepository nhanVienRepository;
    NhanVienMapper nhanVienMapper;
    PasswordEncoder passwordEncoder;
    ChucVuRepository chucVuRepository;

    public void add(NhanVienCreationRequest request) {
        NhanVien nhanVien = nhanVienMapper.toNhanVien(request);

        String lastMa = nhanVienRepository.findTopByOrderByIdDesc()
                .map(nv -> "NV" + (Integer.parseInt(nv.getMa().substring(2)) + 1))
                .orElse("NV1");

        nhanVien.setMa(lastMa);
        nhanVien.setMatKhau(passwordEncoder.encode(request.getMatKhau()));

        ChucVu chucVu = chucVuRepository.findByMa(PredefinedRole.ADMIN_ROLE)
                .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại: " + PredefinedRole.ADMIN_ROLE));

        nhanVien.setChucVu(chucVu);

        nhanVienRepository.save(nhanVien);
    }

    public NhanVien findByTaiKhoan(String taiKhoan) {
        return nhanVienRepository.findByTaiKhoan(taiKhoan).orElse(null);
    }
}
