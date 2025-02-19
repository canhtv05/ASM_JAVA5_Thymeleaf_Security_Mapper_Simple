package com.canhtv05.asm_java5.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GioHangUpdateRequest {

    @NotNull
    Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate ngayThanhToan;
    @NotNull
    Integer tinhTrang;
}
