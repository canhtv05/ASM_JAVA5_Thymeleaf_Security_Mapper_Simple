package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.service.ChiTietSPService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomeController {

    ChiTietSPService chiTietSPService;

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public String home(Model model) {
        model.addAttribute("spct", chiTietSPService.findAll());
        return "/pages/home/home";
    }
}
