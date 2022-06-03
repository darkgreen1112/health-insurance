package com.darrylion.health_insurance.repository;

import com.darrylion.health_insurance.entity.PatientPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientPolicyRepository extends JpaRepository<PatientPolicy, Long> {
}
