package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long>, JpaSpecificationExecutor<Clinic> {
    Page<Clinic> findByAddress (String address, Pageable pageable);

    @Query(value = "SELECT * FROM clinics WHERE address LIKE %:address%", nativeQuery = true)
    Page<Clinic> findClinicsByAddress(@Param("address") String address, Pageable pageable);

    Page<Clinic> findByEmail (String email, Pageable pageable);

    Optional<Clinic> findByEmail (String email);

    Optional<Clinic> findByClinicName (String email);

    Optional<Clinic> findByPhone (String email);

}
