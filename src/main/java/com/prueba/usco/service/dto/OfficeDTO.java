package com.prueba.usco.service.dto;

import com.prueba.usco.domain.AbstractAuditingEntity;
import lombok.Builder;
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
@Builder
public class OfficeDTO{
    private UUID id;

    private String code;

    private String address;

    private Long cityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfficeDTO)) {
            return false;
        }
        return id != null && id.equals(((OfficeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
