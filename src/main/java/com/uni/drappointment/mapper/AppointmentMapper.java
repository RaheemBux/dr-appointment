package com.uni.drappointment.mapper;

import com.uni.drappointment.dto.AppointmentDTO;
import com.uni.drappointment.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentMapper APPOINTMENT_MAPPER = Mappers.getMapper(AppointmentMapper.class);

    AppointmentEntity toEntity(AppointmentDTO appointmentDTO);
    AppointmentDTO toDto(AppointmentEntity appointmentEntity);
    List<AppointmentDTO> toDtoList(List<AppointmentEntity> appointmentEntities);
}
