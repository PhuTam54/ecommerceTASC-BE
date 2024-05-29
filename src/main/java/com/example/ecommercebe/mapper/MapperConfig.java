package com.example.ecommercebe.mapper;

import com.example.ecommercebe.mapper.ClinicMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ClinicMapper clinicMapper() {
        return ClinicMapper.INSTANCE;
    }
}
