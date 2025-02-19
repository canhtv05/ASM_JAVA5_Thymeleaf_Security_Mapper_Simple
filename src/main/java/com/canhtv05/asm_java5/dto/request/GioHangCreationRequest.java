package com.canhtv05.asm_java5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class GioHangCreationRequest {

    Integer idKH;
    @Size(max = 20)
    @NotNull
    String ma;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayTao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayThanhToan;
    @Size(max = 50)
    String tenNguoiNhan;
    @Size(max = 100)
    String diaChi;
    @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại phải có 10-11 chữ số")
    @Size(max = 30)
    String sdt;
    @NotNull
    Integer tinhTrang;
}
