package com.example.ecommercebe.helper.mapper;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClinicMapper {
    ClinicMapper INSTANCE = Mappers.getMapper(ClinicMapper.class);

    ClinicDTO clinicToClinicDTO(Clinic clinic);
    Clinic clinicDTOToClinic(ClinicDTO clinicDTO);
    void updateEntityFromDto(ClinicDTO clinicDTO, @MappingTarget Clinic clinic);
}
