package com.prueba.usco.service;

import com.prueba.usco.domain.Diagnosis;
import com.prueba.usco.repository.DiagnosisRepository;
import com.prueba.usco.service.dto.DiagnosisDTO;
import com.prueba.usco.service.mapper.DiagnosisMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DiagnosisService {

    private final Logger log = LoggerFactory.getLogger(DiagnosisService.class);

    private final DiagnosisRepository diagnosisRepository;

    private final DiagnosisMapper diagnosisMapper;

    @Transactional(readOnly = true)
    public Optional<DiagnosisDTO> findBy(UUID id) {
        log.debug("Request to get all Products");
        return diagnosisRepository.findById(id).map(diagnosisMapper::toDto);
    }

    public DiagnosisDTO save(DiagnosisDTO diagnosisDTO) {
        log.debug("Request to save debt : {}", diagnosisDTO);
        Diagnosis diagnosis = diagnosisMapper.toEntity(diagnosisDTO);
        diagnosis = diagnosisRepository.save(diagnosis);
        return diagnosisMapper.toDto(diagnosis);
    }
}
