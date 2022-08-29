package com.prueba.usco.service.mapper;

import com.prueba.usco.domain.CityDepartment;
import com.prueba.usco.domain.Diagnosis;
import com.prueba.usco.service.dto.CityDepartmentDTO;
import com.prueba.usco.service.dto.DiagnosisDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {})
public interface CityDepartmentMapper extends EntityMapper<CityDepartmentDTO, CityDepartment> {

    CityDepartmentDTO toDto(CityDepartment cityDepartment);

    CityDepartment toEntity(CityDepartmentDTO cityDepartmentDTO);

    default CityDepartment fromId(Long id) {
        if (id == null) {
            return null;
        }
        CityDepartment cityDepartment = new CityDepartment();
        cityDepartment.setId(id);
        return cityDepartment;
    }
}
