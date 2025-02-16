package com.canhtv05.asm_java5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MauSacCreationRequest {

    @Size(max = 20)
    @NotBlank
    String ma;
    @Size(max = 30)
    @NotBlank
    String ten;
}
