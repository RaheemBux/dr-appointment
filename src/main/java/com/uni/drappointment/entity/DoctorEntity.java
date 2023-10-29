package com.uni.drappointment.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "doctor")
@Where(clause = "status = 1")
public class DoctorEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "consultation_charges")
    private Double consultationCharges;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;
}
