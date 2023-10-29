package com.uni.drappointment.dto;

import com.uni.drappointment.entity.DoctorEntity;
import com.uni.drappointment.entity.PatientEntity;
import com.uni.drappointment.util.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class AppointmentDTO extends AbstractDTO{

    private Long id;
    private Date appointmentDate;
    private String appointmentTime;
    private AppointmentStatus appointmentStatus;
    private DoctorEntity doctor;
    private PatientEntity patient;

}
