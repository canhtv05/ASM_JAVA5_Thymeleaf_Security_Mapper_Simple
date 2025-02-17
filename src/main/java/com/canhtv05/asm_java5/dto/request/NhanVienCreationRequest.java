package com.canhtv05.asm_java5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class NhanVienCreationRequest {

    String ma;
    @NotBlank
    @Size(max = 30)
    String ten;
    @NotBlank
    @Size(max = 30)
    String tenDem;
    @NotBlank
    @Size(max = 30)
    String ho;
    @Builder.Default
    @NotBlank
    @Size(max = 10)
    String gioiTinh = "Nam";
    @NotNull
    @Past(message = "Ngày sinh không hợp lệ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngaySinh;
    @NotBlank
    @Size(max = 100)
    String diaChi;
    @NotBlank
    @Size(max = 30)
    String sdt;
    @NotBlank
    String matKhau;
    @NotBlank
    String taiKhoan;
    @Builder.Default
    @NotNull
    Integer trangThai = 1;
}