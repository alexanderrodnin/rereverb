package com.rereverb.order.config;

import com.rereverb.order.entity.Entities;
import com.rereverb.order.repository.Repositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {Repositories.class})
@EntityScan(basePackageClasses = Entities.class)
public class JpaConfig { }
