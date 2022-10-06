package com.prueba.usco.web.rest;

import com.prueba.usco.domain.User;
import com.prueba.usco.repository.UserRepository;
import com.prueba.usco.security.AuthoritiesConstants;
import com.prueba.usco.service.AppointmentService;
import com.prueba.usco.service.OfficeService;
import com.prueba.usco.service.UserService;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.OfficeDTO;
import com.prueba.usco.service.dto.UserDTO;
import com.prueba.usco.web.rest.errors.BadRequestException;
import com.prueba.usco.web.rest.input.CreateAppointmentInput;
import com.prueba.usco.web.rest.input.CreateOfficeInput;
import com.prueba.usco.web.rest.input.CreateUserInput;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class UserResource {

    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList(
            "id",
            "login",
            "firstName",
            "lastName",
            "email",
            "activated",
            "langKey",
            "createdBy",
            "createdDate",
            "lastModifiedBy",
            "lastModifiedDate"
        )
    );

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    private final UserRepository userRepository;

    private final AppointmentService appointmentService;

    private final OfficeService officeService;

    /**
     * {@code POST  /admin/users}  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, or with status {@code 400 (Bad Request)} if the login or email is already in use.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestException {@code 400 (Bad Request)} if the login or email is already in use.
     */
    @PostMapping("/users")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<User> createUser(@RequestBody CreateUserInput userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        User newUser = userService.createUser(userDTO);
        return ResponseEntity
                .created(new URI("/api/admin/users/" + newUser.getLogin()))
                .body(newUser);
    }

    @PostMapping("/office")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<OfficeDTO> createOffice(@RequestBody CreateOfficeInput officeInput) throws URISyntaxException {
        log.debug("REST request to save User : {}", officeInput);
        OfficeDTO officeDTO = OfficeDTO.builder()
                .code(RandomStringUtils.randomAlphanumeric(5).toUpperCase())
                .address(officeInput.getAddress())
                .cityId(officeInput.getCityId())
                .build();
        return ResponseEntity
                .ok()
                .body(officeService.save(officeDTO));
    }

    @PostMapping("/appointment")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody CreateAppointmentInput appointmentInput) throws URISyntaxException {
        log.debug("REST request to save User : {}", appointmentInput);
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .code(RandomStringUtils.randomAlphanumeric(8).toUpperCase())
                .activated(true)
                .date(appointmentInput.getDate())
                .userId(appointmentInput.getUserId())
                .doctorId(appointmentInput.getDoctorId())
                .officeId(appointmentInput.getOfficeId())
                .build();
        return ResponseEntity
                .ok()
                .body(appointmentService.save(appointmentDTO));
    }

    /**
     * {@code GET /admin/users} : get all users with all the details - calling this are only allowed for the administrators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/users")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
        log.debug("REST request to get all User for an admin");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }

        final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    @GetMapping("/offices")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<List<OfficeDTO>> getAllOffice(Pageable pageable) {
        log.debug("REST request to get all User for an admin");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }

        final Page<OfficeDTO> page = officeService.getAll(pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }
}
