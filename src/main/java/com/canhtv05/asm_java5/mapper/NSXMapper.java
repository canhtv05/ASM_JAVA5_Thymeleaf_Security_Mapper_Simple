package com.canhtv05.asm_java5.mapper;

import com.canhtv05.asm_java5.dto.request.NSXCreationRequest;
import com.canhtv05.asm_java5.dto.request.NSXUpdateRequest;
import com.canhtv05.asm_java5.dto.response.NSXResponse;
import com.canhtv05.asm_java5.entity.Nsx;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NSXMapper {

    Nsx toNSX(NSXCreationRequest request);

    Nsx toNSXUpdate(NSXUpdateRequest request);

    NSXResponse toNSXSResponse(Nsx request);
}
