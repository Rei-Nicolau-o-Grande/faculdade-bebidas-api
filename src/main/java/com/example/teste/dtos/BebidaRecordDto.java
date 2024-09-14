package com.example.teste.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BebidaRecordDto(@NotBlank String name, @NotNull BigDecimal value, String description) {

}
