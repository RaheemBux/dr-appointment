package com.uni.drappointment.mapper;

import com.uni.drappointment.dto.HospitalDTO;
import com.uni.drappointment.entity.HospitalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospitalMapper {

    HospitalMapper HOSPITAL_MAPPER = Mappers.getMapper(HospitalMapper.class);

    HospitalEntity toEntity(HospitalDTO hospitalDTO);
    HospitalDTO toDto(HospitalEntity hospitalEntity);
    List<HospitalDTO> toDtoList(List<HospitalEntity> hospitalEntities);
}
