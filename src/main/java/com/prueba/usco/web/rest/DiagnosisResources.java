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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DiagnosisResources {

    private final Logger log = LoggerFactory.getLogger(AppointmentResource.class);

    private final DiagnosisService diagnosisService;

    @GetMapping("/diagnosis/{diagnosis-id}")
    public ResponseEntity<List<DiagnosisDTO>> getOneDiagnosis(@PathVariable("diagnosis-id") String id) throws URISyntaxException {
        log.debug("REST request to save User : {}", id);

        return new ResponseEntity<>(diagnosisService.findByAppointmentId(UUID.fromString(id)), HttpStatus.OK);
    }

    @PostMapping("/diagnosis")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.DOCTOR + "\")")
    public ResponseEntity<DiagnosisDTO> createDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", diagnosisDTO);
        return ResponseEntity
                .ok()
                .body(diagnosisService.save(diagnosisDTO));
    }
}
