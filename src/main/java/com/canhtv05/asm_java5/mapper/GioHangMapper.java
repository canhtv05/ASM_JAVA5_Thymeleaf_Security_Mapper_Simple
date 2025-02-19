package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.GioHangCreationRequest;
import com.canhtv05.asm_java5.dto.request.GioHangUpdateRequest;
import com.canhtv05.asm_java5.dto.response.GioHangResponse;
import com.canhtv05.asm_java5.entity.GioHang;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = "spring")
public interface GioHangMapper {

    GioHang toGioHang(GioHangCreationRequest request);

    GioHang toGioHangUpdate(GioHangUpdateRequest request);

    GioHangResponse toGioHangResponse(GioHang request);
}
