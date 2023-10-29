package com.uni.drappointment.mapper;

import com.uni.drappointment.dto.DoctorScheduleDTO;
import com.uni.drappointment.entity.DoctorScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorScheduleMapper {

    DoctorScheduleMapper DOCTOR_SCHEDULE_MAPPER = Mappers.getMapper(DoctorScheduleMapper.class);

    DoctorScheduleEntity toEntity(DoctorScheduleDTO doctorScheduleDTO);
    DoctorScheduleDTO toDto(DoctorScheduleEntity doctorScheduleEntity);
    List<DoctorScheduleDTO> toDtoList(List<DoctorScheduleEntity> doctorScheduleEntities);
}
