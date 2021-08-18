package com.rereverb.advertisement.repository;

import com.rereverb.advertisement.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, UUID> {

    List<AdvertisementEntity> findByUserId(UUID userId);
}
