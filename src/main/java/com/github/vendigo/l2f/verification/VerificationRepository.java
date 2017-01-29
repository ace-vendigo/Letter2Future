package com.github.vendigo.l2f.verification;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VerificationRepository extends CrudRepository<Verification, Long> {
    Optional<Verification> findByToken(String token);
    Optional<Verification> findByUserId(Long userId);
}
