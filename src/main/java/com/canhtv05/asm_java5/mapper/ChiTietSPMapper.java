package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.ChiTietSPCreationRequest;
import com.canhtv05.asm_java5.dto.request.ChiTietSPUpdateRequest;
import com.canhtv05.asm_java5.dto.response.ChiTietSPResponse;
import com.canhtv05.asm_java5.entity.ChiTietSP;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChiTietSPMapper {

    ChiTietSP toChiTietSP(ChiTietSPCreationRequest request);

    ChiTietSP toChiTietSPUpdate(ChiTietSPUpdateRequest request);

    ChiTietSPResponse toChiTietSPResponse(ChiTietSP chiTietSP);
}
