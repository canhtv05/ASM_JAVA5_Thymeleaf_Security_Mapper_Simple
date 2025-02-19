//package com.canhtv05.asm_java5.service;
//
//import com.canhtv05.asm_java5.entity.CustomUserDetails;
//import com.canhtv05.asm_java5.entity.KhachHang;
//import com.canhtv05.asm_java5.entity.NhanVien;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class CustomUserDetailsService implements UserDetailsService {
//
//    NhanVienService nhanVienService;
//    KhachHangService khachHangService;
//
//    @Override
//    public UserDetails loadUserByUsername(String ma) throws UsernameNotFoundException {
//        NhanVien nhanVien = nhanVienService.findByTaiKhoan(ma);
//        if (nhanVien != null) {
//            return customUserDetails(nhanVien.getTaiKhoan(), nhanVien.getMatKhau(), nhanVien.getChucVu().getMa(),
//                    nhanVien.getTen());
//        }
//
//        KhachHang khachHang = khachHangService.findByTaiKhoan(ma);
//        if (khachHang != null) {
//            return customUserDetails(khachHang.getTaiKhoan(), khachHang.getMatKhau(), khachHang.getChucVu().getMa(),
//                    khachHang.getTen());
//        }
//
//        throw new UsernameNotFoundException("User not found with ID: " + ma);
//    }
//
//    private CustomUserDetails customUserDetails(String taiKhoan, String matKhau,
//                                                String maCV, String ten) {
//        return new CustomUserDetails(taiKhoan, matKhau,
//                List.of(new SimpleGrantedAuthority("ROLE_" + maCV)), ten);
//    }
//}
