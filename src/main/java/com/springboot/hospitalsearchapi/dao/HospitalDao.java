package com.springboot.hospitalsearchapi.dao;

import com.springboot.hospitalsearchapi.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class HospitalDao {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(DataSource dataSource, JdbcTemplate jdbcTemplate){
        this.dataSource=dataSource;
        this.jdbcTemplate=jdbcTemplate;
    }

    public void add(final Hospital hospital){
        this.jdbcTemplate.update("Insert into nation_wide_hospital values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(), hospital.getManagementNumber(),
                hospital.getLicenseDate(), hospital.getBusinessStatus(), hospital.getBusinessStatusCode(), hospital.getPhone(),
                hospital.getFullAddress(), hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getTotalAreaSize());
    }
}
