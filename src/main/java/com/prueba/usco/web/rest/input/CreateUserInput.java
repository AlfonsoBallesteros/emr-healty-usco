package com.prueba.usco.web.rest.input;

import com.prueba.usco.domain.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInput {

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate birthdate;

    private String documentId;

    private String phoneNumber;

    private String address;

    private Set<String> authorities;
}
