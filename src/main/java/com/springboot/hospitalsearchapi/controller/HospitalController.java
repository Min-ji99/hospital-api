package com.springboot.hospitalsearchapi.controller;

import com.springboot.hospitalsearchapi.HospitalSearchApiApplication;
import com.springboot.hospitalsearchapi.dao.HospitalDao;
import com.springboot.hospitalsearchapi.domain.Hospital;
import com.springboot.hospitalsearchapi.domain.dto.HospitalRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospital-api")
public class HospitalController {
    HospitalDao hospitalDao;
    public HospitalController(HospitalDao hospitalDao){
        this.hospitalDao=hospitalDao;
    }

    @GetMapping(value="/search/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id){
        Hospital hospital=hospitalDao.findById(id);
        Optional<Hospital> opt=Optional.of(hospital);

        if(!opt.isEmpty()) {
            return ResponseEntity.ok().body(hospital);
        }else{
            return ResponseEntity.badRequest().body(new Hospital());
        }
    }

    @GetMapping(value="/search/name/{name}")
    public ResponseEntity<List<Hospital>> getByName(@PathVariable("name") String name){
        List<Hospital> hospitalList=hospitalDao.findByName(name);
        
        if(!hospitalList.isEmpty()){
            return ResponseEntity.ok().body(hospitalList);
        }else{
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
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
