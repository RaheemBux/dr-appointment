package com.uni.drappointment.mapper;

import com.uni.drappointment.dto.DoctorDTO;
import com.uni.drappointment.entity.DoctorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);

    DoctorEntity toEntity(DoctorDTO doctorDTO);
    DoctorDTO toDto(DoctorEntity doctorEntity);
    List<DoctorDTO> toDtoList(List<DoctorEntity> doctorEntities);
}
