package com.atguigu.hospital_registry.repostitory;

import com.atguigu.hospital_registry.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
}
