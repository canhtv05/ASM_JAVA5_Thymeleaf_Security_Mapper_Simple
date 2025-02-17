package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.KhachHangCreationRequest;
import com.canhtv05.asm_java5.dto.request.KhachHangUpdateRequest;
import com.canhtv05.asm_java5.dto.response.KhachHangResponse;
import com.canhtv05.asm_java5.entity.KhachHang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {

    KhachHang toKhachHang(KhachHangCreationRequest request);

    KhachHang toKhachHangUpdate(KhachHangUpdateRequest request);

    KhachHangResponse toKhachHangResponse(KhachHang request);
}
