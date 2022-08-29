package com.prueba.usco.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "office", schema = "public")
@Getter
@Setter
@ToString
public class Office extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "address")
    private String address;

    @Column(name = "city_id")
    private Long cityId;

    @ManyToOne
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    private CityDepartment city;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Office)) {
            return false;
        }
        return id != null && id.equals(((Office) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
