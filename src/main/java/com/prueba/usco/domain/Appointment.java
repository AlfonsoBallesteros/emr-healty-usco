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
@Table(name = "appointment", schema = "public")
@Getter
@Setter
@ToString
public class Appointment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "date")
    private Instant date;

    @Column(name = "code")
    private String code;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "justification")
    private String justification;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "doctor_id")
    private UUID doctorId;

    @Column(name = "office_id")
    private UUID officeId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "office_id", insertable = false, updatable = false)
    private Office office;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Appointment)) {
            return false;
        }
        return id != null && id.equals(((Appointment) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
