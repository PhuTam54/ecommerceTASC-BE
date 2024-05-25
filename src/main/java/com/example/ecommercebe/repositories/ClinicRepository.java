package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long>, JpaSpecificationExecutor<Clinic> {
    List<Clinic> findByAddress (String address);
    @Query(value = "SELECT * FROM clinic WHERE address LIKE %:address%", nativeQuery = true)
    List<Clinic> findClinicsByAddress(@Param("address") String address);
}
