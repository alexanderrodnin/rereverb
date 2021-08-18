package com.rereverb.advertisement.config;

import com.rereverb.advertisement.entity.Entities;
import com.rereverb.advertisement.repository.Repositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {Repositories.class})
@EntityScan(basePackageClasses = Entities.class)
public class JpaConfig { }
