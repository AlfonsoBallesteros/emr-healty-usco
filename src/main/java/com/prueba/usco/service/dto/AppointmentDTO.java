package com.prueba.usco.service.dto;

import com.prueba.usco.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
public class AppointmentDTO {

    private UUID id;

    private Instant date;

    private String code;

    private boolean activated;

    private String justification;

    private UUID userId;

    private UUID doctorId;

    private UUID officeId;

    private UserDTO user;

    private UserDTO doctor;

    private OfficeDTO office;

    public UUID getOfficeId() {
        return officeId;
    }

    public void setOfficeId(UUID officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppointmentDTO)) {
            return false;
        }
        return id != null && id.equals(((AppointmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
