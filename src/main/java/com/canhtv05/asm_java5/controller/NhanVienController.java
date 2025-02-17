package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.KhachHangUpdateRequest;
import com.canhtv05.asm_java5.dto.request.NhanVienCreationRequest;
import com.canhtv05.asm_java5.dto.request.NhanVienUpdateRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.NhanVien;
import com.canhtv05.asm_java5.service.NhanVienService;
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
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {

    NhanVienService nhanVienService;

    @GetMapping
    public String viewAll(@ModelAttribute NhanVien nhanVien, Model model) {
        model.addAttribute("nhanVien", nhanVienService.findAll());
        return "/pages/nhanvien/nhan-vien";
    }

    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute(name = "nhanVien") NhanVienCreationRequest nhanVien, Model model) {
        model.addAttribute("nhanVien", nhanVien);
        return "/pages/nhanvien/nhan-vien-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "nhanVien") NhanVienCreationRequest request,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", request);
            return "/pages/nhanvien/nhan-vien-add";
        }

        try {
            nhanVienService.add(request);
            return "redirect:/admin/nhan-vien";
        } catch (DuplicateKeyException e) {
            model.addAttribute("duplicateTK", e.getMessage());
            model.addAttribute("nhanVien", request);
            return "/pages/nhanvien/nhan-vien-add";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        return "/pages/nhanvien/nhan-vien-detail";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("nhanVien", nhanVienService.findById(id));
        return "/pages/nhanvien/nhan-vien-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(name = "nhanVien") NhanVienUpdateRequest request,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", request);
            return "/pages/nhanvien/nhan-vien-update";
        }

        nhanVienService.update(request);
        return "redirect:/admin/nhan-vien";
    }
}
