package com.uni.drappointment.entity;

import com.uni.drappointment.util.AccessType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "user")
@Where(clause = "status = 1")
public class UserEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "login_date")
    private Timestamp loginDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "access_type")
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @Column(name = "user_type_id")
    private Integer userTypeId;

}
