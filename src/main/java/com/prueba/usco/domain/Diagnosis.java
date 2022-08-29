package com.prueba.usco.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "diagnosis", schema = "public")
@Getter
@Setter
@ToString
public class Diagnosis extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "appointment_id")
    private UUID appointmentId;

    @ManyToOne
    @JoinColumn(name = "appointment_id", insertable = false, updatable = false)
    private Appointment appointment;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "diagnosis_medicine",
            joinColumns = { @JoinColumn(name = "diagnosis_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "medicine_id", referencedColumnName = "id") }
    )
    @BatchSize(size = 20)
    private Set<Medicine> medicine = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Diagnosis)) {
            return false;
        }
        return id != null && id.equals(((Diagnosis) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
