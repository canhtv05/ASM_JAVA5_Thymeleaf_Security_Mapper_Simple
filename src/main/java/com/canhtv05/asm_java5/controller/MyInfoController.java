package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.entity.NhanVien;
import com.canhtv05.asm_java5.mapper.KhachHangMapper;
import com.canhtv05.asm_java5.mapper.NhanVienMapper;
import com.canhtv05.asm_java5.service.KhachHangService;
import com.canhtv05.asm_java5.service.NhanVienService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyInfoController {

    NhanVienService nhanVienService;
    KhachHangService khachHangService;
    NhanVienMapper nhanVienMapper;
    KhachHangMapper khachHangMapper;

    @GetMapping("/my-info")
    public String myInfo(Model model) {
        String username = getUsername();

        NhanVien nhanVien = nhanVienService.findByTaiKhoan(username);
        if (nhanVien != null) {
            model.addAttribute("nv", nhanVienMapper.toNhanVienResponse(nhanVien));
            return "pages/myinfo/my-info";
        }

        KhachHang khachHang = khachHangService.findByTaiKhoan(username);
        if (khachHang != null) {
            model.addAttribute("kh", khachHangMapper.toKhachHangResponse(khachHang));
            return "pages/myinfo/my-info";
        }

        throw new NullPointerException("NOT FOUND USER");
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        String username = getUsername();
        NhanVien nhanVien = nhanVienService.findByTaiKhoan(username);
        if (nhanVien != null) {
            model.addAttribute("nv", nhanVienMapper.toNhanVienResponse(nhanVien));
            return "pages/myinfo/change-password";
        }

        KhachHang khachHang = khachHangService.findByTaiKhoan(username);
        if (khachHang != null) {
            model.addAttribute("kh", khachHangMapper.toKhachHangResponse(khachHang));
            return "pages/myinfo/change-password";
        }

        throw new NullPointerException("NOT FOUND USER");
    }

    private String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        return customUserDetails.getUsername();
    }
}
