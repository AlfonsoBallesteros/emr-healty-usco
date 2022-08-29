package com.prueba.usco.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "city_department", schema = "public")
@Getter
@Setter
@ToString
public class CityDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private CityDepartment parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CityDepartment)) {
            return false;
        }
        return id != null && id.equals(((CityDepartment) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
