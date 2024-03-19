package com.test.cryptography.repository;

import com.test.cryptography.entity.EncoderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncoderRepository extends JpaRepository<EncoderEntity , Long> {
}
