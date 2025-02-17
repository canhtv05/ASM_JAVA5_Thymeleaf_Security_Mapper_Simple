package com.canhtv05.asm_java5.dto.request;

import com.canhtv05.asm_java5.entity.ChucVu;
import jakarta.validation.constraints.*;
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
public class KhachHangUpdateRequest {

    @NotNull
    Integer id;
    String ma;
    @Size(max = 30)
    @NotBlank
    String ten;
    @Size(max = 30)
    String tenDem;
    @Size(max = 30)
    String ho;
    @NotNull
    @Past(message = "Ngày sinh không hợp lệ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngaySinh;
    @Size(max = 30)
    @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại phải có 10-11 chữ số")
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
    @NotBlank
    String taiKhoan;
    ChucVu chucVu;
}
