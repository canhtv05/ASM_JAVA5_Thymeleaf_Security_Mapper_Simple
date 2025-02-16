package com.canhtv05.asm_java5.dto.response;

import com.canhtv05.asm_java5.entity.DongSP;
import com.canhtv05.asm_java5.entity.MauSac;
import com.canhtv05.asm_java5.entity.Nsx;
import com.canhtv05.asm_java5.entity.SanPham;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietSPResponse {
    Integer id;
    Integer namBH;
    String moTa;
    Integer soLuongTon;
    Double giaNhap;
    Double giaBan;
    SanPham sanPham;
    Nsx nsx;
    MauSac mauSac;
    DongSP dongSP;
}