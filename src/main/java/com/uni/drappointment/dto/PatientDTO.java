package com.uni.drappointment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class PatientDTO extends AbstractDTO{

    private Long id;
    private String name;
    private String gender;
    private Date dateOfBirth;
    private String email;
    private String contact;
    private Integer age;
}
