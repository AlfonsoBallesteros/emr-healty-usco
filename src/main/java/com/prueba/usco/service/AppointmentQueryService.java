package com.prueba.usco.service;

import com.prueba.usco.domain.Appointment;
import com.prueba.usco.domain.Office;
import com.prueba.usco.repository.AppointmentRepository;
import com.prueba.usco.service.criteria.AppointmentCriteria;
import com.prueba.usco.service.dto.AppointmentDTO;
import com.prueba.usco.service.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentQueryService extends QueryService<Appointment>{

    private final Logger log = LoggerFactory.getLogger(AppointmentQueryService.class);

    private final AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper;

    @Transactional(readOnly = true)
    public Page<AppointmentDTO> findByCriteria(AppointmentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Appointment> specification = createSpecification(criteria);
        return appointmentRepository.findAll(specification, page)
                .map(appointmentMapper::toDto);
    }

    @SneakyThrows
    protected Specification<Appointment> createSpecification(AppointmentCriteria criteria) {
        Specification<Appointment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), "date"));
            }
            if (criteria.getOfficeId() != null) {
                specification = specification.and(buildSpecification(criteria.getOfficeId(), "officeId"));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(), "userId"));
            }
            if (criteria.getDoctorId() != null) {
                specification = specification.and(buildSpecification(criteria.getDoctorId(), "doctorId"));
            }
        }
        return specification;
    }
}
