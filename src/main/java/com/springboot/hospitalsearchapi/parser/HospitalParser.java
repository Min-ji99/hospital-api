package com.springboot.hospitalsearchapi.parser;

import com.springboot.hospitalsearchapi.domain.Hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HospitalParser implements Parser<Hospital>{

    @Override
    public Hospital parse(String str) {
        String[] splitted = str.split("\",\"");
        Hospital hospital = new Hospital();
        hospital.setId(Integer.parseInt(splitted[0].replace("\"","")));
        hospital.setOpenServiceName(splitted[1]);
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(splitted[3]));
        hospital.setManagementNumber(splitted[4]);

        int year=Integer.parseInt(splitted[5].substring(0, 4));
        int month=Integer.parseInt(splitted[5].substring(4, 6));
        int day=Integer.parseInt(splitted[5].substring(6, 8));
        hospital.setLicenseDate(LocalDateTime.of(year, month, day, 0, 0, 0));

        hospital.setBusinessStatus(Integer.parseInt(splitted[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(splitted[9]));
        hospital.setPhone(splitted[15]);
        hospital.setFullAddress(splitted[18]);
        hospital.setRoadNameAddress(splitted[19]);
        hospital.setHospitalName(splitted[21]);
        hospital.setBusinessTypeName(splitted[25]);
        hospital.setHealthcareProviderCount(Integer.parseInt(splitted[29]));
        hospital.setPatientRoomCount(Integer.parseInt(splitted[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(splitted[31]));
        hospital.setTotalAreaSize(Float.parseFloat(splitted[32]));
        return hospital;
    }
}
