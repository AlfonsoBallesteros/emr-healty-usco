package com.prueba.usco.web.rest.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CreateAppointmentInput {

    private Instant date;

    private UUID doctorId;

    private UUID userId;

    private UUID officeId;
}
