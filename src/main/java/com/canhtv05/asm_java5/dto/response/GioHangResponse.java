package com.canhtv05.asm_java5.dto.response;

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
public class GioHangResponse {

    Integer id;
    Integer idKH;
    String ma;
    LocalDate ngayTao;
    LocalDate ngayThanhToan;
    String tenNguoiNhan;
    String diaChi;
    String sdt;
    Integer tinhTrang;
}
