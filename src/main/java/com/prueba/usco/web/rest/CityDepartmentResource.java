package com.prueba.usco.web.rest;

import com.prueba.usco.service.AppointmentService;
import com.prueba.usco.service.CityDepartmentService;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.CityDepartmentDTO;
import com.prueba.usco.web.rest.input.CanceledAppointmentInput;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CityDepartmentResource {

    private final Logger log = LoggerFactory.getLogger(CityDepartmentResource.class);

    private final CityDepartmentService cityDepartmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<CityDepartmentDTO>> getAllDepartment(@PageableDefault(size = 10) Pageable pageable) {
        List<CityDepartmentDTO> cityDepartments= cityDepartmentService.findAllDepartment(pageable);
        return ResponseEntity.ok().body(cityDepartments);
    }

    @GetMapping("/city/{department-id}")
    public ResponseEntity<List<CityDepartmentDTO>> getAllDepartment(@RequestHeader(value = "department-id", required = false) final UUID id, @PageableDefault(size = 10) Pageable pageable) {
        List<CityDepartmentDTO> cityDepartments= cityDepartmentService.findAllCity(id, pageable);
        return ResponseEntity.ok().body(cityDepartments);
    }
}
