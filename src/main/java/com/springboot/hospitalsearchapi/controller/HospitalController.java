package com.springboot.hospitalsearchapi.controller;

import com.springboot.hospitalsearchapi.dao.HospitalDao;
import com.springboot.hospitalsearchapi.domain.Hospital;
import com.springboot.hospitalsearchapi.parser.ReadLineContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital-api")
public class HospitalController {
    HospitalDao hospitalDao;
    public HospitalController(HospitalDao hospitalDao){
        this.hospitalDao=hospitalDao;
    }
    @GetMapping(value="/search/{id}")
    public Hospital getById(@PathVariable("id") String id){
        return hospitalDao.findById(id);
    }
    @DeleteMapping(value="/delete/all")
    public ResponseEntity<Integer> deleteAll(){
        return ResponseEntity
                .ok()
                .body(hospitalDao.deleteAll());
    }
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") String id){
        return ResponseEntity
                .ok()
                .body(hospitalDao.deleteById(id));
    }
}
