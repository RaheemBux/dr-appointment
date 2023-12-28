package com.uni.drappointment.mapper;

import com.uni.drappointment.dto.UserDTO;
import com.uni.drappointment.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", expression = "java(com.uni.drappointment.util.EncoderDecoder.getEncryptedSHA1Password(userDTO.getPassword()))")
    UserEntity toEntity(UserDTO userDTO);
    UserDTO toDto(UserEntity userEntity);
    List<UserDTO> toDtoList(List<UserEntity> userEntities);
}
