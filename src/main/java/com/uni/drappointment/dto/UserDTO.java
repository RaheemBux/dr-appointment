package com.uni.drappointment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class UserDTO extends AbstractDTO {
    private String id;
    private String userName;
    private String password;
    private Timestamp loginDate;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String email;
}
