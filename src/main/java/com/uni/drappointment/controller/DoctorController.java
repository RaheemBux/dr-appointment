package com.uni.drappointment.controller;

import com.uni.drappointment.dto.DoctorDTO;
import com.uni.drappointment.dto.StatusDTO;
import com.uni.drappointment.entity.DoctorEntity;
import com.uni.drappointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.uni.drappointment.mapper.DoctorMapper.DOCTOR_MAPPER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody DoctorDTO doctorDTO) {
        try {
            DoctorEntity doctorEntity = DOCTOR_MAPPER.toEntity(doctorDTO);
            doctorEntity.setStatus(true);
            doctorService.create(doctorEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Doctor Added Successfully ", DOCTOR_MAPPER.toDto(doctorEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody DoctorDTO doctorDTO) {
        try {
            DoctorEntity doctorEntity = doctorService.findById(doctorDTO.getId());
            if (doctorEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Doctor not found!"), HttpStatus.NOT_FOUND);
            }
            doctorEntity = DOCTOR_MAPPER.toEntity(doctorDTO);
            doctorEntity.setStatus(true);
            doctorService.update(doctorEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Doctor Updated Successfully ", DOCTOR_MAPPER.toDto(doctorEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<DoctorDTO> viewById(@PathVariable Long id) {
        DoctorEntity doctorEntity;
        DoctorDTO doctorDTO = null;
        try {
            doctorEntity = doctorService.findById(id);
            if (doctorEntity != null) {
                doctorDTO = DOCTOR_MAPPER.toDto(doctorEntity);
                return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Doctor not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            DoctorEntity doctorEntity = doctorService.findById(id);
            if (doctorEntity == null) {
                return new ResponseEntity<>(new StatusDTO(1, "Doctor not found!"), HttpStatus.NOT_FOUND);
            } else {
                doctorService.delete(doctorEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Doctor deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<DoctorDTO> getAll() {
        return DOCTOR_MAPPER.toDtoList(doctorService.findAll());
    }

}
