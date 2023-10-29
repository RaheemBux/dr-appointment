package com.uni.drappointment.controller;

import com.uni.drappointment.dto.AppointmentDTO;
import com.uni.drappointment.dto.StatusDTO;
import com.uni.drappointment.entity.AppointmentEntity;
import com.uni.drappointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.uni.drappointment.mapper.AppointmentMapper.APPOINTMENT_MAPPER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            AppointmentEntity appointmentEntity = APPOINTMENT_MAPPER.toEntity(appointmentDTO);
            appointmentEntity.setStatus(true);
            appointmentService.create(appointmentEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Appointment Added Successfully ", APPOINTMENT_MAPPER.toDto(appointmentEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.findById(appointmentDTO.getId());
            if (appointmentEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Doctor not found!"), HttpStatus.NOT_FOUND);
            }
            appointmentEntity = APPOINTMENT_MAPPER.toEntity(appointmentDTO);
            appointmentEntity.setStatus(true);
            appointmentService.update(appointmentEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Appointment Updated Successfully ", APPOINTMENT_MAPPER.toDto(appointmentEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<AppointmentDTO> viewById(@PathVariable Long id) {
        AppointmentEntity appointmentEntity;
        AppointmentDTO appointmentDTO = null;
        try {
            appointmentEntity = appointmentService.findById(id);
            if (appointmentEntity != null) {
                appointmentDTO = APPOINTMENT_MAPPER.toDto(appointmentEntity);
                return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Appointment not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            AppointmentEntity appointmentEntity = appointmentService.findById(id);
            if (appointmentEntity == null) {
                return new ResponseEntity<>(new StatusDTO(1, "Appointment not found!"), HttpStatus.NOT_FOUND);
            } else {
                appointmentService.delete(appointmentEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Appointment deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<AppointmentDTO> getAll() {
        return APPOINTMENT_MAPPER.toDtoList(appointmentService.findAll());
    }

}
