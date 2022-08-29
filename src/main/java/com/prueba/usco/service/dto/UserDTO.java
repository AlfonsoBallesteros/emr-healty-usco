package com.prueba.usco.service.dto;

import com.prueba.usco.domain.Authority;
import com.prueba.usco.domain.User;
import com.prueba.usco.domain.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with only the public attributes.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    private String login;

    private String firstName;

    private String lastName;

    private String documentId;

    private LocalDate birthdate;

    private Gender gender;
    private boolean activated = false;

    private String phoneNumber;

    private String address;

    private Instant createdDate;

    private Set<String> authorities;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender();
        this.documentId = user.getDocumentId();
        this.birthdate = user.getBirthdate();
        this.activated = user.isActivated();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.createdDate = user.getCreatedDate();
        this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
    }
}
