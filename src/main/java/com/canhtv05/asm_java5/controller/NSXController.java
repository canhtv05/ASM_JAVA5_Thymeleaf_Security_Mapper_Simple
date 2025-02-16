package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.NSXCreationRequest;
import com.canhtv05.asm_java5.dto.request.NSXUpdateRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamCreationRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamUpdateRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.Nsx;
import com.canhtv05.asm_java5.entity.SanPham;
import com.canhtv05.asm_java5.service.NSXService;
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
@RequestMapping("/admin/nsx")
public class NSXController {

    NSXService nsxService;

    @GetMapping
    public String viewAll(@ModelAttribute Nsx nsx, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("ten", userDetails.getTen());
        }
        model.addAttribute("nsx", nsxService.findAll());
        return "/pages/product/nsx/nsx";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        nsxService.deleteById(id);
        return "redirect:/admin/nsx";
    }

    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute(name = "nsx") Nsx nsx, Model model) {
        model.addAttribute("nsx", nsx);
        return "/pages/product/nsx/nsx-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "nsx") NSXCreationRequest request,
                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/nsx/nsx-add";
        }

        try {
            nsxService.add(request);
            return "redirect:/admin/nsx";
        } catch (DuplicateKeyException e) {
            model.addAttribute("duplicate", "Duplicate ma: " + request.getMa());
            return "/pages/product/nsx/nsx-add";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("nsx", nsxService.findById(id));
        return "/pages/product/nsx/nsx-detail";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("nsx", nsxService.findById(id));
        return "/pages/product/nsx/nsx-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(name = "nsx") NSXUpdateRequest request,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/nsx/nsx-update";
        }

        nsxService.update(request);
        return "redirect:/admin/nsx";
    }
}
