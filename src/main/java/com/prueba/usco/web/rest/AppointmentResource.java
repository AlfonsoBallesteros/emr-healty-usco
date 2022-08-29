package com.prueba.usco.web.rest;

import com.prueba.usco.security.AuthoritiesConstants;
import com.prueba.usco.service.AppointmentService;
import com.prueba.usco.service.UserService;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.UserDTO;
import com.prueba.usco.web.rest.input.CanceledAppointmentInput;
import com.prueba.usco.web.rest.input.CreateAppointmentInput;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AppointmentResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentResource.class);

    private final AppointmentService appointmentService;

    private final UserService userService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointment(@PageableDefault(size = 10) Pageable pageable) {
        return userService
                .getUserWithAuthorities()
                .map(UserDTO::new)
                .map(UserDTO::getId)
                .map(u -> ResponseEntity.ok().body(appointmentService.findAll(u, pageable)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/appointment/canceled")
    public ResponseEntity<AppointmentDTO> createCancelled(@RequestBody CanceledAppointmentInput canceledInput) throws URISyntaxException {
        log.debug("REST request to save User : {}", canceledInput);

        return appointmentService.findBy(canceledInput.getAppointmentId()).map(ap -> {
            ap.setActivated(false);
            ap.setJustification(canceledInput.getDescription());
            return ResponseEntity.ok().body(appointmentService.save(ap));
        }).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
