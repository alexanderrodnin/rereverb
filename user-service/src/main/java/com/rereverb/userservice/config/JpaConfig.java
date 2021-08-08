package com.rereverb.userservice.config;

import com.rereverb.userservice.entity.Entities;
import com.rereverb.userservice.repository.Repositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {Repositories.class})
@EntityScan(basePackageClasses = Entities.class)
public class JpaConfig { }
