package com.prueba.usco.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
public class DiagnosisDTO {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private String description;

    private UUID appointmentId;

    private AppointmentDTO appointment;

    private Set<MedicineDTO> medicine;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiagnosisDTO)) {
            return false;
        }
        return id != null && id.equals(((DiagnosisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
