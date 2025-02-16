package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.NhanVienCreationRequest;
import com.canhtv05.asm_java5.entity.NhanVien;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {

    NhanVien toNhanVien(NhanVienCreationRequest request);
}
