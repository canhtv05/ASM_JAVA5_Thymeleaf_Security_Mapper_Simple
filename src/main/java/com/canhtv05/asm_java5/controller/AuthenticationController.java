package com.canhtv05.asm_java5.controller;

import com.canhtv05.asm_java5.constant.PredefinedRole;
import com.canhtv05.asm_java5.dto.request.AuthenticationRequest;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.NhanVien;
import com.canhtv05.asm_java5.repository.NhanVienRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {

    NhanVienRepository nhanVienRepository;

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

        NhanVien nhanVien = nhanVienRepository.findByTaiKhoan((request.getTaiKhoan()))
                .orElse(null);

        if (nhanVien == null || !new BCryptPasswordEncoder().matches(request.getMatKhau(), nhanVien.getMatKhau())) {
            model.addAttribute("errUS", "Incorrect username");
            model.addAttribute("errPW", "Incorrect password");
            return "/pages/login/login";
        }

        CustomUserDetails userDetails = new CustomUserDetails(
                request.getTaiKhoan(),
                request.getMatKhau(),
                List.of(new SimpleGrantedAuthority("ROLE_" + PredefinedRole.ADMIN_ROLE)),
                nhanVien.getTen()
        );

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        log.info("User '{}' has roles: {}", userDetails.getUsername(), userDetails.getAuthorities());

        return "redirect:/";
    }
}
