package com.canhtv05.asm_java5.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Please enter your username")
    private String taiKhoan;

    @NotBlank(message = "Please enter your password")
    private String matKhau;
}
