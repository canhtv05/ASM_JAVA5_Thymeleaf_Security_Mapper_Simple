package com.canhtv05.asm_java5.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhachHangCreationRequest {

    @Size(max = 20)
    @NotBlank
    String ma;
    @Size(max = 30)
    @NotBlank
    String ten;
    @Size(max = 30)
    String tenDem;
    @Size(max = 30)
    String ho;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngaySinh;
    @Size(max = 30)
    @NotBlank
    String sdt;
    @Size(max = 100)
    @NotBlank
    String diaChi;
    @Size(max = 50)
    String thanhPho;
    @Size(max = 50)
    String quocGia;
    @NotBlank
    String matKhau;
}
