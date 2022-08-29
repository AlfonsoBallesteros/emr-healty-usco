package com.prueba.usco.service.mapper;

import com.prueba.usco.domain.Appointment;
import com.prueba.usco.domain.Medicine;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.MedicineDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {})
public interface MedicineMapper extends EntityMapper<MedicineDTO, Medicine> {

    MedicineDTO toDto(Medicine medicine);

    Medicine toEntity(MedicineDTO medicineDTO);

    default Medicine fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Medicine medicine = new Medicine();
        medicine.setId(id);
        return medicine;
    }
}
