package com.canhtv05.asm_java5.configuration;

import com.canhtv05.asm_java5.constant.PredefinedRole;
import com.canhtv05.asm_java5.entity.ChucVu;
import com.canhtv05.asm_java5.entity.NhanVien;
import com.canhtv05.asm_java5.repository.ChucVuRepository;
import com.canhtv05.asm_java5.repository.NhanVienRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    ChucVuRepository chucVuRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${app.admin.username}")
    String ADMIN_USERNAME;

    @NonFinal
    @Value("${app.admin.password}")
    String ADMIN_PASSWORD;

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
    )
    ApplicationRunner applicationRunner(NhanVienRepository nhanVienRepository) {
        return args -> {

            if (nhanVienRepository.findByMa(ADMIN_USERNAME).isEmpty()) {
                ChucVu chucVu = chucVuRepository.save(ChucVu.builder()
                        .ma(PredefinedRole.ADMIN_ROLE)
                        .ten(PredefinedRole.ADMIN_ROLE + " ROLE")
                        .build());

                chucVuRepository.save(ChucVu.builder()
                                .ma(PredefinedRole.CUSTOMER_ROLE)
                                .ten(PredefinedRole.CUSTOMER_ROLE + " ROLE")
                        .build());

                NhanVien nhanVien = NhanVien.builder()
                        .taiKhoan(PredefinedRole.ADMIN_ROLE)
                        .chucVu(chucVu)
                        .matKhau(passwordEncoder.encode(ADMIN_PASSWORD))
                        .build();

                nhanVienRepository.save(nhanVien);

                log.warn("STAFF has been created with default `ma` and `password`: STAFF, please change it!");
            }
            log.info("Application initialization completed .....");
        };
    }
}
