package com.prueba.usco.service.mapper;

import com.prueba.usco.domain.Appointment;
import com.prueba.usco.service.dto.AppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OfficeMapper.class})
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, Appointment> {

    AppointmentDTO toDto(Appointment appointment);

    Appointment toEntity(AppointmentDTO appointmentDTO);

    default Appointment fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(id);
        return appointment;
    }
}
