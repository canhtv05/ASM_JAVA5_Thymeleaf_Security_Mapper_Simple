package com.canhtv05.asm_java5.dto.request;

import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.entity.NhanVien;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonChiTietCreationRequest {

    List<Integer> idCTSP;
    Integer idKh;
    List<Integer> soLuong;
    Double donGia;
}
