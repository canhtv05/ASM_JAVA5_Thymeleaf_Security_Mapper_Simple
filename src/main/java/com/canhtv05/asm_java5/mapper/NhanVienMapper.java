package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.KhachHangCreationRequest;
import com.canhtv05.asm_java5.dto.request.KhachHangUpdateRequest;
import com.canhtv05.asm_java5.dto.request.NhanVienCreationRequest;
import com.canhtv05.asm_java5.dto.request.NhanVienUpdateRequest;
import com.canhtv05.asm_java5.dto.response.KhachHangResponse;
import com.canhtv05.asm_java5.entity.KhachHang;
import com.canhtv05.asm_java5.entity.NhanVien;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {

    NhanVien toNhanVien(NhanVienCreationRequest request);

    NhanVien toNhanVienUpdate(NhanVienUpdateRequest request);

    NhanVien toKhachHangSResponse(KhachHang request);
}
