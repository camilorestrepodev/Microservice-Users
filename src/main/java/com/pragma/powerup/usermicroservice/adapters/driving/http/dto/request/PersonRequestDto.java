package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PersonRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Pattern( message = "El correo debe tener el formato correcto.", regexp = "[A-Za-z0-9+_.-]+@(.+\\.[A-Za-z]+)$")
    private String mail;
    @NotBlank
    @Pattern(regexp = "\\+?[0-9]{1,13}")
    @Size(min = 6, max = 13, message = "El numero de celular debe tener entre 6 y 13 digitos.")
    @Positive(message = "El numero de celular no puede ser negativo")
    private String phone;
    @NotBlank
    private String address;
    @NotBlank
    private String idDniType;
    @NotBlank
    private String dniNumber;
    @NotBlank
    private String idPersonType;
    @NotBlank
    private String password;
    @Schema(description = "Birthdate is optional -> The format is dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Nullable
    private LocalDate birthdate;
}
