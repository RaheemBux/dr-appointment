package com.uni.drappointment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoctorScheduleDTO extends AbstractDTO{

    private Long id;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private DoctorDTO doctor;
    private HospitalDTO hospital;
}
