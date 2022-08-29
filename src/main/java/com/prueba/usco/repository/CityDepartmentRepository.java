package com.prueba.usco.repository;

import com.prueba.usco.domain.CityDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityDepartmentRepository extends JpaRepository<CityDepartment, Long> {

    Page<CityDepartment> findAllByParentId(Long id, Pageable pageable);

    Page<CityDepartment> findAllByParentIdIsNull(Pageable pageable);
}
