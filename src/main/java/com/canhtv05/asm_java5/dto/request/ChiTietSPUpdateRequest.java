package com.canhtv05.asm_java5.dto.request;

import com.canhtv05.asm_java5.entity.DongSP;
import com.canhtv05.asm_java5.entity.MauSac;
import com.canhtv05.asm_java5.entity.Nsx;
import com.canhtv05.asm_java5.entity.SanPham;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietSPUpdateRequest {

    @NotNull
    Integer id;
    @Size(max = 50)
    @NotNull
    Integer namBH;
    @NotBlank
    String moTa;
    @NotNull
    Integer soLuongTon;
    @NotNull
    Double giaNhap;
    @NotNull
    Double giaBan;
    @NotNull
    SanPham sanPham;
    @NotNull
    Nsx nsx;
    @NotNull
    DongSP dongSP;
    @NotNull
    MauSac mauSac;
}
