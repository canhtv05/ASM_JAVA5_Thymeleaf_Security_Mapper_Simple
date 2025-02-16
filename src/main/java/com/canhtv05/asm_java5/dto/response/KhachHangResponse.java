package com.canhtv05.asm_java5.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    LocalDate ngaySinh;
    String sdt;
    String diaChi;
    String thanhPho;
    String quocGia;
    String matKhau;
}
