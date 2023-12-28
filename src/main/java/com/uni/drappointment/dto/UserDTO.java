package com.uni.drappointment.dto;

import com.uni.drappointment.util.AccessType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "accessType can not be null")
    private AccessType accessType;
    private Integer userTypeId;
}
