package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PersonNotAgeException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.PersonRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IPersonHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/person/")
@RequiredArgsConstructor
public class PersonRestController {
    private final IPersonHandler personHandler;

    @Operation(summary = "Add a new person",
            responses = {
                @ApiResponse(responseCode = "201", description = "Person created",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                @ApiResponse(responseCode = "400", description = "Person is not of age",
                content = @Content(mediaType = "application/json",schema = @Schema(ref = "#componentes/schemas/Error"))),
                @ApiResponse(responseCode = "409", description = "Person already exists",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping
    public ResponseEntity<Map<String, String>> savePerson(@Valid @RequestBody PersonRequestDto personRequestDto) {
        LocalDate fechaNacimiento = personRequestDto.getBirthdate();
        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        if (edad < 18) {
            throw new PersonNotAgeException("the user must be of legal age");
        }
        personHandler.savePerson(personRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PERSON_CREATED_MESSAGE));
    }
}
