package com.uni.drappointment.mapper;

import com.uni.drappointment.dto.PatientDTO;
import com.uni.drappointment.entity.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientMapper PATIENT_MAPPER = Mappers.getMapper(PatientMapper.class);

    PatientEntity toEntity(PatientDTO patientDTO);
    PatientDTO toDto(PatientEntity patientEntity);
    List<PatientDTO> toDtoList(List<PatientEntity> patientEntities);
}
