package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.SanPhamCreationRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamUpdateRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.SanPham;
import com.canhtv05.asm_java5.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    SanPhamService sanPhamService;

    @GetMapping
    public String viewAll(@ModelAttribute SanPham sanPham, Model model) {
        model.addAttribute("sanPham", sanPhamService.findAll());
        return "/pages/product/sanpham/san-pham";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sanPhamService.deleteById(id);
        return "redirect:/admin/san-pham";
    }

    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute(name = "sanPham") SanPham sanPham, Model model) {
        model.addAttribute("sanPham", sanPham);
        return "/pages/product/sanpham/san-pham-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "sanPham") SanPhamCreationRequest request,
                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/sanpham/san-pham-add";
        }

        try {
            sanPhamService.add(request);
            return "redirect:/admin/san-pham";
        } catch (DuplicateKeyException e) {
            model.addAttribute("duplicate", "Duplicate ma: " + request.getMa());
            return "/pages/product/sanpham/san-pham-add";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("sanPham", sanPhamService.findById(id));
        return "/pages/product/sanpham/san-pham-detail";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("sanPham", sanPhamService.findById(id));
        return "/pages/product/sanpham/san-pham-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(name = "sanPham") SanPhamUpdateRequest request,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/sanpham/san-pham-update";
        }

        sanPhamService.update(request);
        return "redirect:/admin/san-pham";
    }
}
