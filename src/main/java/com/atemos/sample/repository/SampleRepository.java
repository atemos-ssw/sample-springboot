package com.atemos.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atemos.sample.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long>, SampleRepositoryCustom {

}
