package com.canhtv05.asm_java5.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DongSPResponse {
    Integer id;
    String ma;
    String ten;
}