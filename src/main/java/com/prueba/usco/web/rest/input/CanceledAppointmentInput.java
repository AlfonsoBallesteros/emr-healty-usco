package com.prueba.usco.web.rest.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class CanceledAppointmentInput {

    private UUID appointmentId;

    private String description;

}
