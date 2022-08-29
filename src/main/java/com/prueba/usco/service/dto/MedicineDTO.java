package com.prueba.usco.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
public class MedicineDTO implements Serializable {

    private UUID id;

    private String name;

    private String filing;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicineDTO)) {
            return false;
        }
        return id != null && id.equals(((MedicineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
