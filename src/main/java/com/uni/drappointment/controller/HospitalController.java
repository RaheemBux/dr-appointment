package com.uni.drappointment.controller;

import com.uni.drappointment.dto.HospitalDTO;
import com.uni.drappointment.dto.StatusDTO;
import com.uni.drappointment.entity.HospitalEntity;
import com.uni.drappointment.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.uni.drappointment.mapper.HospitalMapper.HOSPITAL_MAPPER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @PostMapping(value = "/create")
    public ResponseEntity<StatusDTO> create(@RequestBody HospitalDTO hospitalDTO) {
        try {
            HospitalEntity hospitalEntity = HOSPITAL_MAPPER.toEntity(hospitalDTO);
            hospitalEntity.setStatus(true);
            hospitalService.create(hospitalEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Hospital Added Successfully ", HOSPITAL_MAPPER.toDto(hospitalEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @PutMapping(value = "/update")
    public ResponseEntity<StatusDTO> update(@RequestBody HospitalDTO HospitalDTO) {
        try {
            HospitalEntity hospitalEntity = hospitalService.findById(HospitalDTO.getId());
            if (hospitalEntity == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Hospital not found!"), HttpStatus.NOT_FOUND);
            }
            hospitalEntity = HOSPITAL_MAPPER.toEntity(HospitalDTO);
            hospitalEntity.setStatus(true);
            hospitalService.update(hospitalEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Hospital Updated Successfully ", HOSPITAL_MAPPER.toDto(hospitalEntity)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.OK);
        }
    }
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<HospitalDTO> viewById(@PathVariable Long id) {
        HospitalEntity hospitalEntity;
        HospitalDTO hospitalDTO = null;
        try {
            hospitalEntity = hospitalService.findById(id);
            if (hospitalEntity != null) {
                hospitalDTO = HOSPITAL_MAPPER.toDto(hospitalEntity);
                return new ResponseEntity<>(hospitalDTO, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Hospital not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Exception occurred!", HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {
        try {
            HospitalEntity HospitalEntity = hospitalService.findById(id);
            if (HospitalEntity == null) {
                return new ResponseEntity<>(new StatusDTO(1, "Hospital not found!"), HttpStatus.NOT_FOUND);
            } else {
                hospitalService.delete(HospitalEntity);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.OK);

        }
        return new ResponseEntity<>(new StatusDTO(1, "Hospital deleted Successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public List<HospitalDTO> getAll() {
        return HOSPITAL_MAPPER.toDtoList(hospitalService.findAll());
    }

}
