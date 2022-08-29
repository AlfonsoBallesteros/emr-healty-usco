package com.prueba.usco.web.rest;

import com.prueba.usco.security.AuthoritiesConstants;
import com.prueba.usco.service.DiagnosisService;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.DiagnosisDTO;
import com.prueba.usco.service.dto.OfficeDTO;
import com.prueba.usco.web.rest.input.CanceledAppointmentInput;
import com.prueba.usco.web.rest.input.CreateOfficeInput;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DiagnosisResources {

    private final Logger log = LoggerFactory.getLogger(AppointmentResource.class);

    private final DiagnosisService diagnosisService;

    @GetMapping("/diagnosis/{diagnosis-id}")
    public ResponseEntity<DiagnosisDTO> getOneDiagnosis(@RequestHeader(value = "diagnosis-id", required = false) final UUID id) throws URISyntaxException {
        log.debug("REST request to save User : {}", id);

        return diagnosisService.findBy(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/office")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.DOCTOR + "\")")
    public ResponseEntity<DiagnosisDTO> createUser(@RequestBody DiagnosisDTO diagnosisDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", diagnosisDTO);
        return ResponseEntity
                .ok()
                .body(diagnosisService.save(diagnosisDTO));
    }
}
