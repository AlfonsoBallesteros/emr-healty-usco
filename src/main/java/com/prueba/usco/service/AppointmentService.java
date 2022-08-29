package com.prueba.usco.service;

import com.prueba.usco.domain.Appointment;
import com.prueba.usco.repository.AppointmentRepository;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final Logger log = LoggerFactory.getLogger(AppointmentService.class);

    private final AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper;

    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        log.debug("Request to save debt : {}", appointmentDTO);
        Appointment debt = appointmentMapper.toEntity(appointmentDTO);
        debt = appointmentRepository.save(debt);
        return appointmentMapper.toDto(debt);
    }

    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all banks {}", pageable);
        return appointmentRepository.findAll(pageable).stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<AppointmentDTO> findBy(UUID id) {
        log.debug("Request to get all Products");
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDto);
    }

}
