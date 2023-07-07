package com.atemos.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atemos.sample.entity.AmrInfo;

public interface AMRInfoRepository extends JpaRepository<AmrInfo, Integer>{
	Optional<AmrInfo> findByServerId(Integer amrInfoId);

}
