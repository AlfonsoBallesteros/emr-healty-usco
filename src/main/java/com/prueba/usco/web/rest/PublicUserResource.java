package com.prueba.usco.web.rest;

import com.prueba.usco.security.AuthoritiesConstants;
import com.prueba.usco.service.UserService;
import com.prueba.usco.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PublicUserResource {

    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList("id", "login", "firstName", "lastName", "email", "activated", "langKey")
    );

    private final Logger log = LoggerFactory.getLogger(PublicUserResource.class);

    private final UserService userService;

    public PublicUserResource(UserService userService) {
        this.userService = userService;
    }

    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }

    /**
     * Gets a list of all roles.
     * @return a string list of all roles.
     */
    @GetMapping("/authorities")
    public List<String> getAuthorities() {
        return userService.getAuthorities();
    }
}
