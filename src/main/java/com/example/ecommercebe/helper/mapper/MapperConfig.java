package com.example.ecommercebe.helper.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ClinicMapper clinicMapper() {
        return ClinicMapper.INSTANCE;
    }
}
