package com.prueba.usco.repository;

import com.prueba.usco.domain.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<Office, UUID> {
}
