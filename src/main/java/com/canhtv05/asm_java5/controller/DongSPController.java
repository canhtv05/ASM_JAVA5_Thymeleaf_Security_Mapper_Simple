package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.DongSpCreationRequest;
import com.canhtv05.asm_java5.dto.request.DongSpUpdateRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.DongSP;
import com.canhtv05.asm_java5.service.DongSPService;
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
@RequestMapping("/admin/dong-sp")
public class DongSPController {

    DongSPService dongSPService;

    @GetMapping
    public String viewAll(@ModelAttribute DongSP dongSP, Model model) {
        model.addAttribute("dsp", dongSPService.findAll());
        return "/pages/product/dongsp/dong-sp";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        dongSPService.deleteById(id);
        return "redirect:/admin/dong-sp";
    }

    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute(name = "dongSP") DongSpCreationRequest request, Model model) {
        model.addAttribute("dongSP", request);
        return "/pages/product/dongsp/dong-sp-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "dongSP") DongSpCreationRequest request,
                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/dongsp/dong-sp-add";
        }

        try {
            dongSPService.add(request);
            return "redirect:/admin/dong-sp";
        } catch (DuplicateKeyException e) {
            model.addAttribute("duplicate", "Duplicate ma: " + request.getMa());
            return "/pages/product/dongsp/dong-sp-add";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("dongSP", dongSPService.findById(id));
        return "/pages/product/dongsp/dong-sp-detail";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("dongSP", dongSPService.findById(id));
        return "/pages/product/dongsp/dong-sp-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(name = "dongSP") DongSpUpdateRequest request,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/dongsp/dong-sp-update";
        }

        dongSPService.update(request);
        return "redirect:/admin/dong-sp";
    }
}
