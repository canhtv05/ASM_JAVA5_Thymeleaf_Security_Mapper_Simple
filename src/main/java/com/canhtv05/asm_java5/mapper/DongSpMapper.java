package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.DongSpCreationRequest;
import com.canhtv05.asm_java5.dto.request.DongSpUpdateRequest;
import com.canhtv05.asm_java5.dto.response.DongSPResponse;
import com.canhtv05.asm_java5.entity.DongSP;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DongSpMapper {

    DongSP toDongSP(DongSpCreationRequest request);

    DongSP toDongSPUpdate(DongSpUpdateRequest request);

    DongSPResponse toDongSPSResponse(DongSP request);
}
