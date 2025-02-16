package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.constant.PredefinedRole;
import com.canhtv05.asm_java5.entity.CustomUserDetails;
import com.canhtv05.asm_java5.entity.NhanVien;
import com.canhtv05.asm_java5.repository.NhanVienRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetailsService implements UserDetailsService {

    NhanVienRepository nhanVienRepository;

    @Override
    public UserDetails loadUserByUsername(String maNV) throws UsernameNotFoundException {
        NhanVien nhanVien = nhanVienRepository.findByMa(maNV)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(
                nhanVien.getTaiKhoan(),
                nhanVien.getMatKhau(),
                List.of(new SimpleGrantedAuthority("ROLE_" + PredefinedRole.ADMIN_ROLE)),
                nhanVien.getTen()
        );
    }
}
