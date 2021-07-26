package com.dong.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.dong.hosp.mapper")
public class HospConfig {
}
