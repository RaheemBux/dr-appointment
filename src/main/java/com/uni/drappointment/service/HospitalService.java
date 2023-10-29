package com.uni.drappointment.service;

import com.uni.drappointment.entity.HospitalEntity;

import java.util.List;

public interface HospitalService {
    List<HospitalEntity> findAll();
    HospitalEntity create(HospitalEntity hospitalEntity) ;
    HospitalEntity delete(HospitalEntity hospitalEntity);
    HospitalEntity update(HospitalEntity hospitalEntity);
    HospitalEntity findById(Long id);
}
