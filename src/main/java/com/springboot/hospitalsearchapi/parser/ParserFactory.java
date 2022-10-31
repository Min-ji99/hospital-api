package com.springboot.hospitalsearchapi.parser;

import com.springboot.hospitalsearchapi.domain.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserFactory {
    @Bean
    public ReadLineContext<Hospital> hospitalReadLineContextest(){
        return new ReadLineContext<Hospital>(new HospitalParser());
    }
}
