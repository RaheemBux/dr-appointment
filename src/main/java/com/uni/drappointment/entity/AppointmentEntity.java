package com.uni.drappointment.entity;

import com.uni.drappointment.util.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "appointment")
@Where(clause = "status = 1")
public class AppointmentEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "appointment_time")
    private String appointmentTime;

    @Column(name = "appointment_status")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

}
