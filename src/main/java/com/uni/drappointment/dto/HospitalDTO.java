package com.uni.drappointment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HospitalDTO extends AbstractDTO{

    private Long id;
    private String name;
    private String location;
    private String contact;
    private String operatingHours;
}
