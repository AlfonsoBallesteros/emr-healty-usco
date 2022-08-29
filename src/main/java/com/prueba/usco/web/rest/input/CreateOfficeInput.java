package com.prueba.usco.web.rest.input;

import com.prueba.usco.service.dto.CityDepartmentDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class CreateOfficeInput {

    private String address;

    private Long cityId;
}
