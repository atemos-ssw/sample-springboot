package com.atemos.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atemos.sample.entity.AuthToken;

public interface AuthRepository extends JpaRepository<AuthToken, Integer> {

	Optional<AuthToken> findByCustomerId(Integer id);

	Optional<AuthToken> findByToken(String token);
}
