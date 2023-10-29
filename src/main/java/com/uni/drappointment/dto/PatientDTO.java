package com.uni.drappointment.dto;

import com.uni.drappointment.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class PatientDTO extends AbstractEntity{

    private Long id;
    private String name;
    private String gender;
    private Date dateOfBirth;
    private String email;
    private String contact;
    private Integer age;
}
