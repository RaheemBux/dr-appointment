package com.uni.drappointment.controller;

import com.uni.drappointment.dto.AuthenticatedUser;
import com.uni.drappointment.dto.StatusDTO;
import com.uni.drappointment.entity.UserEntity;
import com.uni.drappointment.security.JwtAuthenticationRequest;
import com.uni.drappointment.security.JwtTokenUtil;
import com.uni.drappointment.security.UsersAuthenticationProvider;
import com.uni.drappointment.service.UserService;
import com.uni.drappointment.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UsersAuthenticationProvider usersAuthenticationProvider;

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        logger.info("Inside Auth Service");

        AuthenticatedUser authenticatedUser;
        if(jwtAuthenticationRequest.getUsername()!=null && !jwtAuthenticationRequest.getUsername().isEmpty() && jwtAuthenticationRequest.getPassword()!=null && !jwtAuthenticationRequest.getPassword().isEmpty()){
            UserEntity user = userService.findByUserName(jwtAuthenticationRequest.getUsername());
            if(user == null){
                return new ResponseEntity<>(new StatusDTO(0, "Incorrect User Name or Password" ), HttpStatus.NOT_FOUND);
            }
            final Authentication authentication = usersAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtAuthenticationRequest.getUsername(),
                    jwtAuthenticationRequest.getPassword()
            ));
            logger.info("Inside Auth Service :: 1");
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Inside Auth Service :: 2");
                authenticatedUser = getAuthenticatedUserForUnblockedUser(user, jwtTokenUtil);
                user.setLoginDate(CommonUtil.getCurrentTimestamp());
                user.setIsLogged(true);
                userService.update(user);
                return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new StatusDTO(0, "Incorrect User Name or Password" ), HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>((new UserEntity()), HttpStatus.OK);
    }
    private AuthenticatedUser getAuthenticatedUserForUnblockedUser(UserEntity user, JwtTokenUtil jwtTokenUtil) {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setCode(1);
        authenticatedUser.setStatus(1);

        if (user.getFirstName() != null) {
            authenticatedUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            authenticatedUser.setLastName(user.getLastName());
        }
        if(user.getMobileNo()!=null){
            authenticatedUser.setMobileNo(user.getMobileNo());
        }
        if (user.getUserName() != null) {
            authenticatedUser.setUserName(user.getUserName());
        }
        if (user.getEmail() != null) {
            authenticatedUser.setEmail(user.getEmail());
        }
        if (user.getLoginDate() != null) {
            authenticatedUser.setLoginDate(user.getLoginDate().toString());
        }
        final String token = jwtTokenUtil.generateToken(user.getUserName());
        authenticatedUser.setMessage("Success");
        authenticatedUser.setToken(token);
        authenticatedUser.setUserId(user.getId());
        return authenticatedUser;
    }

}

