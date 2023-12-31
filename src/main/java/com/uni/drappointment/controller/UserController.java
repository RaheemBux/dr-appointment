package com.uni.drappointment.controller;

import com.uni.drappointment.dto.StatusDTO;
import com.uni.drappointment.dto.UserDTO;
import com.uni.drappointment.entity.UserEntity;
import com.uni.drappointment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.uni.drappointment.mapper.UserMapper.USER_MAPPER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            boolean error = false;
            String errorMsg = "";

            UserEntity oldUser = userService.findByUserName(userDTO.getUserName());
            if (oldUser != null) {
                if(oldUser.getStatus()) {
                    error = true;
                    errorMsg = userDTO.getUserName() + " userName already exists! ";
                }
            }
            oldUser = userService.findByEmail(userDTO.getEmail());
            if (oldUser != null) {
                error = true;
                errorMsg += userDTO.getEmail() + " emailId already exists! ";
            }
            oldUser = userService.findByMobileNo(userDTO.getMobileNo().toUpperCase());
            if (oldUser != null) {
                error = true;
                errorMsg += userDTO.getMobileNo() + " mobile# already exists! ";
            }
            if (error) {
                return new ResponseEntity<>(new StatusDTO(0, errorMsg), HttpStatus.OK);
            }
            if(!(userDTO.getEmail().contains("@"))){
                return new ResponseEntity<>(new StatusDTO(1, "Incorrect Email"), HttpStatus.OK);
            }

            UserEntity user = USER_MAPPER.toEntity(userDTO);
            user.setStatus(true);
            userService.create(user);
            return new ResponseEntity<>(new StatusDTO(1, "User Added Successfully ",USER_MAPPER.toDto(user)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity<StatusDTO> updateUser(@RequestBody UserDTO userDTO) {
        try {
            boolean error = false;
            String errorMsg = "";
            UserEntity oldUser;
            UserEntity user;
            user=userService.findById(Long.parseLong(userDTO.getId()));
            if (user== null) {
                return new ResponseEntity<>(new StatusDTO(0, "User not found!"), HttpStatus.NOT_FOUND);
            } else {
                if (user.getUserName().equals(userDTO.getUserName()) && !(user.getUserName().equalsIgnoreCase(userDTO.getUserName()))) {
                    oldUser = userService.findByUserName(userDTO.getUserName());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getUserName() + " userName already exists! ";
                    }
                }
                if (user.getEmail().equals(userDTO.getEmail()) && !(user.getEmail().equalsIgnoreCase(userDTO.getEmail()))) {
                    oldUser = userService.findByEmail(userDTO.getEmail());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getEmail() + " emailId already exists! ";
                    }
                }
                if (user.getMobileNo().equals(userDTO.getMobileNo()) && !(user.getMobileNo().equalsIgnoreCase(userDTO.getMobileNo()))) {
                    oldUser = userService.findByMobileNo(userDTO.getMobileNo());
                    if (oldUser != null) {
                        error = true;
                        errorMsg += userDTO.getMobileNo() + " mobile# already exists! ";
                    }
                }
                if (error) {
                    return new ResponseEntity<>(new StatusDTO(0, errorMsg), HttpStatus.OK);
                }
                user = USER_MAPPER.toEntity(userDTO);
                user.setStatus(true);
                userService.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StatusDTO(1, "User updated Successfully"), HttpStatus.OK);
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<UserDTO> viewById(@PathVariable Long id) {
        UserEntity user;
        UserDTO userDTO = null;
        try {
            user = userService.findById(id);
            if (user != null) {
                userDTO = USER_MAPPER.toDto(user);
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> deleteUser(@PathVariable Long id) {
        try {
            UserEntity user = userService.findById(id);

            if (user == null) {
                return new ResponseEntity<>(new StatusDTO(1, "User not found!"), HttpStatus.NOT_FOUND);
            } else {
                userService.delete(user);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "User deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<UserDTO> getAll() {
        List<UserEntity> userEntities =userService.findAll();
        return USER_MAPPER.toDtoList(userEntities);
    }

}