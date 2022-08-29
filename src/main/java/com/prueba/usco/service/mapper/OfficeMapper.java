package com.prueba.usco.service.mapper;

import com.prueba.usco.domain.Appointment;
import com.prueba.usco.domain.Office;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.OfficeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {CityDepartmentMapper.class})
public interface OfficeMapper extends EntityMapper<OfficeDTO, Office> {

    @Mapping(source = "city.id", target = "cityId")
    OfficeDTO toDto(Office office);

    @Mapping(source = "cityId", target = "city")
    Office toEntity(OfficeDTO officeDTO);

    default Office fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Office office = new Office();
        office.setId(id);
        return office;
    }
}
