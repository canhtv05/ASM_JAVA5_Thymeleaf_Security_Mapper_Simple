package com.canhtv05.asm_java5.dto.response;

import com.canhtv05.asm_java5.entity.ChucVu;
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
public class KhachHangResponse {

    Integer id;
    String ma;
    String ten;
    String tenDem;
    String ho;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngaySinh;
    String sdt;
    String diaChi;
    String thanhPho;
    String quocGia;
    String taiKhoan;
    String matKhau;
    ChucVu chucVu;
}
