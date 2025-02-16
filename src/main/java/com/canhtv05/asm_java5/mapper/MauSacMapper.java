package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.MauSacCreationRequest;
import com.canhtv05.asm_java5.dto.request.MauSacUpdateRequest;
import com.canhtv05.asm_java5.dto.response.MauSacResponse;
import com.canhtv05.asm_java5.entity.MauSac;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MauSacMapper {

    MauSac toMauSac(MauSacCreationRequest request);

    MauSac toMauSacUpdate(MauSacUpdateRequest request);

    MauSacResponse toMauSacResponse(MauSac request);
}
