package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.SanPhamCreationRequest;
import com.canhtv05.asm_java5.dto.request.SanPhamUpdateRequest;
import com.canhtv05.asm_java5.dto.response.ChucVuResponse;
import com.canhtv05.asm_java5.dto.response.SanPhamResponse;
import com.canhtv05.asm_java5.entity.ChucVu;
import com.canhtv05.asm_java5.entity.SanPham;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChucVuMapper {

    ChucVu toChucVu(ChucVuResponse response);

    ChucVuResponse toChucVuResponse(ChucVu request);
}
