package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.entity.HoaDon;
import com.canhtv05.asm_java5.service.HoaDonChiTietService;
import com.canhtv05.asm_java5.service.HoaDonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/hoa-don")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonController {

    HoaDonService hoaDonService;
    HoaDonChiTietService hoaDonChiTietService;

    @GetMapping
    public String viewAll(Model model) {
        model.addAttribute("hoaDon", hoaDonService.findAll());
        return "pages/hoadon/hoa-don";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("hoaDonChiTiet", hoaDonChiTietService.findByHoaDonId(id));
        return "/pages/hoadon/hoa-don-detail";
    }
}
