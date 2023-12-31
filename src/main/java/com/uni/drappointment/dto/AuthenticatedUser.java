package com.uni.drappointment.dto;

import com.uni.drappointment.util.AccessType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AuthenticatedUser {
    private String userName;
    private Integer code;
    private Integer status;
    private String token;
    private Long userId;
    private String email;
    private String mobileNo;
    private String firstName;
    private String lastName;
    private String message;
    private AccessType accessType;

    public AuthenticatedUser(Integer status , String message){
        this.status = status;
        this.message = message;
    }
}
