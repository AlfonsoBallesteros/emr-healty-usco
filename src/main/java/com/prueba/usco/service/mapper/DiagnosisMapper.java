package com.prueba.usco.service.mapper;

import com.prueba.usco.domain.Diagnosis;
import com.prueba.usco.domain.Office;
import com.prueba.usco.repository.DiagnosisRepository;
import com.prueba.usco.service.dto.DiagnosisDTO;
import com.prueba.usco.service.dto.OfficeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class})
public interface DiagnosisMapper extends EntityMapper<DiagnosisDTO, Diagnosis> {

    DiagnosisDTO toDto(Diagnosis diagnosis);

    Diagnosis toEntity(DiagnosisDTO diagnosisDTO);

    default Diagnosis fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(id);
        return diagnosis;
    }

}
