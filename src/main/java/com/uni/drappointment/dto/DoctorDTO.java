package com.uni.drappointment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DoctorDTO extends AbstractDTO{

    private Long id;
    private String name;
    private Double consultationCharges;
    private String speciality;
    private String email;
    private String contact;
}
