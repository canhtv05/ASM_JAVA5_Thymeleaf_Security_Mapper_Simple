package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.dto.request.AuthenticationRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.entity.NhanVien;
import com.canhtv05.asm_java5.service.KhachHangService;
import com.canhtv05.asm_java5.service.NhanVienService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {

    NhanVienService nhanVienService;
    KhachHangService khachHangService;
    PasswordEncoder passwordEncoder;

    @GetMapping("/login-form")
    public String loginForm(@ModelAttribute(name = "req") AuthenticationRequest request, Model model) {
        model.addAttribute("req", request);
        return "/pages/login/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute(name = "req") AuthenticationRequest request,
                        BindingResult bindingResult,
                        HttpSession httpSession,
                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("req", request);
            return "/pages/login/login";
        }

        NhanVien nhanVien = nhanVienService.findByTaiKhoan(request.getTaiKhoan());
        if (!Objects.isNull(nhanVien) && passwordEncoder.matches(request.getMatKhau(), nhanVien.getMatKhau())) {
            CustomUserDetails userDetails = new CustomUserDetails(
                    request.getTaiKhoan(),
                    request.getMatKhau(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + nhanVien.getChucVu().getMa())),
                    nhanVien.getTen()
            );
            authenticateUser(userDetails, httpSession);
            return "redirect:/";
        }

        KhachHang khachHang = khachHangService.findByTaiKhoan(request.getTaiKhoan());
        if (!Objects.isNull(khachHang) && passwordEncoder.matches(request.getMatKhau(), khachHang.getMatKhau())) {
            CustomUserDetails userDetails = new CustomUserDetails(
                    request.getTaiKhoan(),
                    request.getMatKhau(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + khachHang.getChucVu().getMa())),
                    khachHang.getTen()
            );
            authenticateUser(userDetails, httpSession);
            return "redirect:/";
        }

        model.addAttribute("errUS", "Incorrect username");
        model.addAttribute("errPW", "Incorrect password");
        return "/pages/login/login";
    }

    private void authenticateUser(CustomUserDetails userDetails, HttpSession httpSession) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        log.info("User '{}' has roles: {}", userDetails.getUsername(), userDetails.getAuthorities());
    }
}
