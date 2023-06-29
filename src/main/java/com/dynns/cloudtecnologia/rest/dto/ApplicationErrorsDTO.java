package com.dynns.cloudtecnologia.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationErrorsDTO {
    List<String> errors;
}
