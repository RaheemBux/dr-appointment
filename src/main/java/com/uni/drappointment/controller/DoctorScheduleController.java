package com.uni.drappointment.controller;

import com.uni.drappointment.dto.DoctorScheduleDTO;
import com.uni.drappointment.dto.StatusDTO;
import com.uni.drappointment.entity.DoctorScheduleEntity;
import com.uni.drappointment.service.DoctorScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.uni.drappointment.mapper.DoctorScheduleMapper.DOCTOR_SCHEDULE_MAPPER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/doctor-schedule")
public class DoctorScheduleController {

    @Autowired
    DoctorScheduleService doctorScheduleService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody DoctorScheduleDTO doctorScheduleDTO) {
        try {
            DoctorScheduleEntity doctorScheduleEntity = DOCTOR_SCHEDULE_MAPPER.toEntity(doctorScheduleDTO);
            doctorScheduleEntity.setStatus(true);
            doctorScheduleService.create(doctorScheduleEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Doctor Schedule Added Successfully ", DOCTOR_SCHEDULE_MAPPER.toDto(doctorScheduleEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody DoctorScheduleDTO doctorScheduleDTO) {
        try {
            DoctorScheduleEntity doctorScheduleEntity = doctorScheduleService.findById(doctorScheduleDTO.getId());
            if (doctorScheduleEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Doctor Schedule not found!"), HttpStatus.NOT_FOUND);
            }
            doctorScheduleEntity = DOCTOR_SCHEDULE_MAPPER.toEntity(doctorScheduleDTO);
            doctorScheduleEntity.setStatus(true);
            doctorScheduleService.update(doctorScheduleEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Doctor Schedule Updated Successfully ", DOCTOR_SCHEDULE_MAPPER.toDto(doctorScheduleEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<DoctorScheduleDTO> viewById(@PathVariable Long id) {
        DoctorScheduleEntity doctorScheduleEntity;
        DoctorScheduleDTO doctorScheduleDTO = null;
        try {
            doctorScheduleEntity = doctorScheduleService.findById(id);
            if (doctorScheduleEntity != null) {
                doctorScheduleDTO = DOCTOR_SCHEDULE_MAPPER.toDto(doctorScheduleEntity);
                return new ResponseEntity<>(doctorScheduleDTO, HttpStatus.OK);
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
            DoctorScheduleEntity doctorScheduleEntity = doctorScheduleService.findById(id);
            if (doctorScheduleEntity == null) {
                return new ResponseEntity<>(new StatusDTO(1, "Doctor Schedule not found!"), HttpStatus.NOT_FOUND);
            } else {
                doctorScheduleService.delete(doctorScheduleEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Doctor Schedule deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<DoctorScheduleDTO> getAll() {
        return DOCTOR_SCHEDULE_MAPPER.toDtoList(doctorScheduleService.findAll());
    }

}
