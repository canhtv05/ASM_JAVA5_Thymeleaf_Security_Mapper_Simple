package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.constant.PredefinedRole;
import com.canhtv05.asm_java5.dto.request.KhachHangCreationRequest;
import com.canhtv05.asm_java5.dto.request.KhachHangUpdateRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamCreationRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamUpdateRequest;
import com.canhtv05.asm_java5.entity.ChucVu;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.entity.SanPham;
import com.canhtv05.asm_java5.mapper.ChucVuMapper;
import com.canhtv05.asm_java5.service.ChucVuService;
import com.canhtv05.asm_java5.service.KhachHangService;
import com.canhtv05.asm_java5.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    KhachHangService khachHangService;

    @GetMapping
    public String viewAll(@ModelAttribute KhachHang khachHang, Model model) {
        model.addAttribute("khachHang", khachHangService.findAll());
        return "/pages/khachhang/khach-hang";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        khachHangService.deleteById(id);
        return "redirect:/admin/khach-hang";
    }

    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute(name = "khachHang") KhachHang khachHang, Model model) {
        model.addAttribute("khachHang", khachHang);
        return "/pages/khachhang/khach-hang-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "khachHang") KhachHangCreationRequest request,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("khachHang", request);
            return "/pages/khachhang/khach-hang-add";
        }

        try {
            khachHangService.add(request);
            return "redirect:/admin/khach-hang";
        } catch (DuplicateKeyException e) {
            model.addAttribute("duplicateTK", e.getMessage());
            model.addAttribute("khachHang", request);
            return "/pages/khachhang/khach-hang-add";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("khachHang", khachHangService.findById(id));
        return "/pages/khachhang/khach-hang-detail";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("khachHang", khachHangService.findById(id));
        return "/pages/khachhang/khach-hang-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(name = "khachHang") KhachHangUpdateRequest request,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("khachHang", request);
            return "/pages/khachhang/khach-hang-update";
        }

        khachHangService.update(request);
        return "redirect:/admin/khach-hang";
    }
}
