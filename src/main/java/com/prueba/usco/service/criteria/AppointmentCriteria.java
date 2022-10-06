package com.prueba.usco.service.criteria;

import com.prueba.usco.service.filter.InstantFilter;
import com.prueba.usco.service.filter.UUIDFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString
public class AppointmentCriteria  implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private InstantFilter date;

    private UUIDFilter userId;

    private UUIDFilter doctorId;

    private UUIDFilter officeId;

    public AppointmentCriteria() {
    }

    public AppointmentCriteria(AppointmentCriteria other) {
        this.date = other.date == null ? null : other.date.copy();
        this.officeId = other.officeId == null ? null : other.officeId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.doctorId = other.doctorId == null ? null : other.doctorId.copy();
    }

    @Override
    public AppointmentCriteria copy() {
        return new AppointmentCriteria(this);
    }

    public InstantFilter getDate() {
        return date;
    }

    public void setDate(InstantFilter date) {
        this.date = date;
    }

    public UUIDFilter getUserId() {
        return userId;
    }

    public void setUserId(UUIDFilter userId) {
        this.userId = userId;
    }

    public UUIDFilter getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUIDFilter doctorId) {
        this.doctorId = doctorId;
    }

    public UUIDFilter getOfficeId() {
        return officeId;
    }

    public void setOfficeId(UUIDFilter officeId) {
        this.officeId = officeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AppointmentCriteria that = (AppointmentCriteria) o;
        return
                Objects.equals(date, that.date) &&
                Objects.equals(officeId, that.officeId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(doctorId, that.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                date,
                officeId,
                doctorId,
                userId
        );
    }
}
