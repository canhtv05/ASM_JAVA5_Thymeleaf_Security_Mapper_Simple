package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.MauSacCreationRequest;
import com.canhtv05.asm_java5.dto.request.MauSacUpdateRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.MauSac;
import com.canhtv05.asm_java5.service.MauSacService;
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
@RequestMapping("/admin/mau-sac")
public class MauSacController {

    MauSacService mauSacService;

    @GetMapping
    public String viewAll(@ModelAttribute MauSac mauSac, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            model.addAttribute("ten", userDetails.getTen());
        }
        model.addAttribute("mauSac", mauSacService.findAll());
        return "/pages/product/mausac/mau-sac";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        mauSacService.deleteById(id);
        return "redirect:/admin/mau-sac";
    }

    @GetMapping("/view-add")
    public String viewAdd(@ModelAttribute(name = "mauSac") MauSac request, Model model) {
        model.addAttribute("mauSac", request);
        return "/pages/product/mausac/mau-sac-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "mauSac") MauSacCreationRequest request,
                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/mausac/mau-sac-add";
        }

        try {
            mauSacService.add(request);
            return "redirect:/admin/mau-sac";
        } catch (DuplicateKeyException e) {
            model.addAttribute("duplicate", "Duplicate ma: " + request.getMa());
            return "/pages/product/mausac/mau-sac-add";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("mauSac", mauSacService.findById(id));
        return "/pages/product/mausac/mau-sac-detail";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("mauSac", mauSacService.findById(id));
        return "/pages/product/mausac/mau-sac-update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(name = "mauSac") MauSacUpdateRequest request,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/pages/product/mausac/mau-sac-update";
        }

        mauSacService.update(request);
        return "redirect:/admin/mau-sac";
    }
}
