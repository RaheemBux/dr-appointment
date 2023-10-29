package com.uni.drappointment.controller;

import com.uni.drappointment.dto.PatientDTO;
import com.uni.drappointment.dto.StatusDTO;
import com.uni.drappointment.entity.PatientEntity;
import com.uni.drappointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.uni.drappointment.mapper.PatientMapper.PATIENT_MAPPER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody PatientDTO patientDTO) {
        try {
            PatientEntity patientEntity = PATIENT_MAPPER.toEntity(patientDTO);
            patientEntity.setStatus(true);
            patientService.create(patientEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Patient Added Successfully ", PATIENT_MAPPER.toDto(patientEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody PatientDTO patientDTO) {
        try {
            PatientEntity patientEntity = patientService.findById(patientDTO.getId());
            if (patientEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Patient not found!"), HttpStatus.NOT_FOUND);
            }
            patientEntity = PATIENT_MAPPER.toEntity(patientDTO);
            patientEntity.setStatus(true);
            patientService.update(patientEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Patient Updated Successfully ", PATIENT_MAPPER.toDto(patientEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<PatientDTO> viewById(@PathVariable Long id) {
        PatientEntity patientEntity;
        PatientDTO patientDTO = null;
        try {
            patientEntity = patientService.findById(id);
            if (patientEntity != null) {
                patientDTO = PATIENT_MAPPER.toDto(patientEntity);
                return new ResponseEntity<>(patientDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Patient not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            PatientEntity patientEntity = patientService.findById(id);
            if (patientEntity == null) {
                return new ResponseEntity<>(new StatusDTO(1, "Patient not found!"), HttpStatus.NOT_FOUND);
            } else {
                patientService.delete(patientEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Patient deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<PatientDTO> getAll() {
        return PATIENT_MAPPER.toDtoList(patientService.findAll());
    }

}
