package com.springboot.hospitalsearchapi.dao;

import com.springboot.hospitalsearchapi.domain.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class HospitalDao {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    RowMapper<Hospital> rowMapper=new RowMapper<Hospital>() {
        @Override
        public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
            //String -> LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(rs.getString("license_date"), formatter);
            Hospital hospital=new Hospital(
                    rs.getInt("id"), rs.getString("open_service_name"), rs.getInt("open_local_government_code"),
                    rs.getString("management_number"), date, rs.getInt("business_status"),
                    rs.getInt("business_status_code"), rs.getString("phone"), rs.getString("full_address"),
                    rs.getString("road_name_address"), rs.getString("hospital_name"), rs.getString("business_type_name"),
                    rs.getInt("healthcare_provider_count"), rs.getInt("patient_room_count"), rs.getInt("total_number_of_beds"),
                    rs.getFloat("total_area_size"));
            return hospital;
        }
    };

    public HospitalDao(DataSource dataSource, JdbcTemplate jdbcTemplate){
        this.dataSource=dataSource;
        this.jdbcTemplate=jdbcTemplate;
    }
    //INSERT INTO `hospital`.`nation_wide_hospitals`
    // (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_data`, `business_status`,
    // `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`,
    // `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`) VALUES
    // ('1', '의원', '3620000', 'PHMA119993620020041100004', '19990612', '1', '13', '062-515-2875',
    // '광주광역시 북구 풍향동 565번지 4호 3층', '광주광역시 북구 동문대로 24, 3층 (풍향동)', '효치과의원', '치과의원', '1', '0', '0');

    public void add(final Hospital hospital){
        String sql = "Insert into nation_wide_hospitals values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(sql, hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(), hospital.getBusinessStatusCode(),
                hospital.getPhone(), hospital.getFullAddress(), hospital.getRoadNameAddress(), hospital.getHospitalName(),
                hospital.getBusinessTypeName(), hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(), hospital.getTotalAreaSize());
    }
    public Hospital findById(String id){
        return this.jdbcTemplate.queryForObject("select * from nation_wide_hospitals where id=?", rowMapper, id);
    }
    public void deleteAll(){
        this.jdbcTemplate.update("delete from nation_wide_hospitals");
    }
    public void deleteById(String id){
        this.jdbcTemplate.update("delete from nation_wide_hospitals where id=?", id);
    }
    public int getCount(){
        return this.jdbcTemplate.queryForObject(
                "select count(*) from nation_wide_hospitals", Integer.class);
    }
}
