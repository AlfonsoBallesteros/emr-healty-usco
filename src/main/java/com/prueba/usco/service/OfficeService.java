package com.prueba.usco.service;

import com.prueba.usco.domain.Appointment;
import com.prueba.usco.domain.Office;
import com.prueba.usco.repository.AppointmentRepository;
import com.prueba.usco.repository.OfficeRepository;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.OfficeDTO;
import com.prueba.usco.service.dto.UserDTO;
import com.prueba.usco.service.mapper.AppointmentMapper;
import com.prueba.usco.service.mapper.OfficeMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficeService {

    private final Logger log = LoggerFactory.getLogger(OfficeService.class);

    private final OfficeRepository officeRepository;

    private final OfficeMapper officeMapper;

    public OfficeDTO save(OfficeDTO officeDTO) {
        log.debug("Request to save office : {}", officeDTO);
        Office office = officeMapper.toEntity(officeDTO);
        office = officeRepository.save(office);
        return officeMapper.toDto(office);
    }

    @Transactional(readOnly = true)
    public Page<OfficeDTO> getAll(Pageable pageable) {
        return officeRepository.findAll(pageable).map(officeMapper::toDto);
    }
}
