package com.springboot.hospitalsearchapi.parser;

import com.springboot.hospitalsearchapi.dao.HospitalDao;
import com.springboot.hospitalsearchapi.domain.Hospital;
import com.springboot.hospitalsearchapi.service.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalParserTest {
    String line1="\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;

    @Autowired
    HospitalService hospitalService;

    /*
    @Test
    @DisplayName("만건 이상 데이터가 파싱되는지 ")
    void name() throws IOException {
        String filename="/Users/minji/Documents/likelion/file/fulldata_01_01_02_P_의원.csv";
        List<Hospital> hospitalList=hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitalList.size()>10000);
    }
    @Test
    @DisplayName("병원정보 전체 add 잘되는지")
    void addAllTest() throws IOException {
        String filename="/Users/minji/Documents/likelion/file/fulldata_01_01_02_P_의원.csv";
        //hospitalDao.deleteAll();
        int cnt=hospitalService.insertLargeVolumeHospitalData(filename);
        assertEquals(cnt, hospitalDao.getCount());
    }
    @Test
    @DisplayName("Hospital이 insert가 잘되는지")
    void addTest(){
        HospitalParser hp=new HospitalParser();
        //hospitalDao.deleteAll();
        Hospital hospital= hp.parse(line1);
        hospitalDao.add(hospital);
    }
    @Test
    @DisplayName("Hospital deleteAll, getCount 잘 되는지")
    void deleteAllTest(){
        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
    }
    @Test
    @DisplayName("findById가 잘 되는지")
    void findByIdTest(){
        HospitalParser hp=new HospitalParser();
        //hospitalDao.deleteAll();
        //Hospital hospital= hp.parse(line1);
        //hospitalDao.add(hospital);
        Hospital findHospital=hospitalDao.findById("1");
        assertEquals("효치과의원", findHospital.getHospitalName());
    }
     */
    @Test
    @DisplayName("csv 1줄을 Hospital로 잘만드는지 Test")
    void convertToHospital(){
        HospitalParser hp=new HospitalParser();
        Hospital hospital=hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode());
        assertEquals
                ("PHMA119993620020041100004",hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate());
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());
    }

}