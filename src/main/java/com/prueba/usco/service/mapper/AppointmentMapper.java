package com.prueba.usco.service.mapper;

import com.prueba.usco.domain.Appointment;
import com.prueba.usco.service.dto.AppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OfficeMapper.class})
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, Appointment> {

    @Mapping(source = "user.id", target = "doctorId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "office.id", target = "officeId")
    AppointmentDTO toDto(Appointment appointment);

    @Mapping(source = "doctorId", target = "user")
    @Mapping(source = "userId", target = "doctor")
    @Mapping(source = "officeId", target = "office")
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
