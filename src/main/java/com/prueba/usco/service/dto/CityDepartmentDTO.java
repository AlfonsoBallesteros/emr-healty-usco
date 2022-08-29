package com.prueba.usco.service.dto;

import com.prueba.usco.domain.AbstractAuditingEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class CityDepartmentDTO {
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    private CityDepartmentDTO parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CityDepartmentDTO)) {
            return false;
        }
        return id != null && id.equals(((CityDepartmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
