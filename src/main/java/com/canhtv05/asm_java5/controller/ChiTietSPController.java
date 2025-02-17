package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.ChiTietSPCreationRequest;
import com.canhtv05.asm_java5.dto.request.ChiTietSPUpdateRequest;
import com.canhtv05.asm_java5.entity.ChiTietSP;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.service.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/admin/chi-tiet-sp")
public class ChiTietSPController {

    ChiTietSPService chiTietSPService;
    SanPhamService sanPhamService;
    NSXService nsxService;
    DongSPService dongSPService;
    MauSacService mauSacService;

    @GetMapping
    public String viewAll(@ModelAttribute ChiTietSP chiTietSP, Model model) {
        model.addAttribute("ctsp", chiTietSPService.findAll());
        return "/pages/product/chitietsp/chi-tiet-sp";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        chiTietSPService.deleteById(id);
        return "redirect:/admin/chi-tiet-sp";
    }

    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute(name = "ctsp") ChiTietSP chiTietSP, Model model) {
        model.addAttribute("ctsp", chiTietSP);
        model.addAttribute("sp", sanPhamService.findAll());
        model.addAttribute("nsx", nsxService.findAll());
        model.addAttribute("dsp", dongSPService.findAll());
        model.addAttribute("ms", mauSacService.findAll());
        return "/pages/product/chitietsp/chi-tiet-sp-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "ctsp") ChiTietSPCreationRequest request,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ctsp", request);
            model.addAttribute("sp", sanPhamService.findAll());
            model.addAttribute("nsx", nsxService.findAll());
            model.addAttribute("dsp", dongSPService.findAll());
            model.addAttribute("ms", mauSacService.findAll());
            return "/pages/product/chitietsp/chi-tiet-sp-add";
        }

        chiTietSPService.add(request);
        return "redirect:/admin/chi-tiet-sp";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("ctsp", chiTietSPService.findById(id));
        return "/pages/product/chitietsp/chi-tiet-sp-detail";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("ctsp", chiTietSPService.findById(id));
        model.addAttribute("sp", sanPhamService.findAll());
        model.addAttribute("nsx", nsxService.findAll());
        model.addAttribute("dsp", dongSPService.findAll());
        model.addAttribute("ms", mauSacService.findAll());
        return "/pages/product/chitietsp/chi-tiet-sp-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(name = "ctsp") ChiTietSPUpdateRequest request,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ctsp", request);
            model.addAttribute("sp", sanPhamService.findAll());
            model.addAttribute("nsx", nsxService.findAll());
            model.addAttribute("dsp", dongSPService.findAll());
            model.addAttribute("ms", mauSacService.findAll());
            return "/pages/product/chitietsp/chi-tiet-sp-update";
        }

        chiTietSPService.update(request);
        return "redirect:/admin/chi-tiet-sp";
    }
}
