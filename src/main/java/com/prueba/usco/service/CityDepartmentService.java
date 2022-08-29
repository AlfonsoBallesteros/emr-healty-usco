package com.prueba.usco.service;

import com.prueba.usco.repository.AppointmentRepository;
import com.prueba.usco.repository.CityDepartmentRepository;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.dto.CityDepartmentDTO;
import com.prueba.usco.service.mapper.AppointmentMapper;
import com.prueba.usco.service.mapper.CityDepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CityDepartmentService {

    private final Logger log = LoggerFactory.getLogger(CityDepartmentService.class);

    private final CityDepartmentRepository cityDepartmentRepository;

    private final CityDepartmentMapper cityDepartmentMapper;

    @Transactional(readOnly = true)
    public List<CityDepartmentDTO> findAllDepartment(Pageable pageable) {
        log.debug("Request to get all banks {}", pageable);
        return cityDepartmentRepository.findAllByParentIdIsNull(pageable).stream()
                .map(cityDepartmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CityDepartmentDTO> findAllCity(Long city, Pageable pageable) {
        log.debug("Request to get all banks {}", pageable);
        return cityDepartmentRepository.findAllByParentId(city, pageable).stream()
                .map(cityDepartmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
