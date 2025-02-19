package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.HoaDonChiTietCreationRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.HoaDonChiTiet;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.service.ChiTietSPService;
import com.canhtv05.asm_java5.service.HoaDonChiTietService;
import com.canhtv05.asm_java5.service.KhachHangService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomeController {

    ChiTietSPService chiTietSPService;
    KhachHangService khachHangService;
    HoaDonChiTietService hoaDonChiTietService;


    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public String home(Model model) {
        model.addAttribute("spct", chiTietSPService.findAll());
        model.addAttribute("khachHang", khachHangService.findAll());
        return "/pages/home/home";
    }

    @PostMapping("/admin/hoa-don/add")
    public String addHoaDon(@ModelAttribute HoaDonChiTietCreationRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        hoaDonChiTietService.add(request, username);

        return "redirect:/";
    }
}
