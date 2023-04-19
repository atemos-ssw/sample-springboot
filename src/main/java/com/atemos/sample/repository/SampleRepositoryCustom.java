package com.atemos.sample.repository;

import java.util.List;
import java.util.Optional;

import com.atemos.sample.entity.Sample;

public interface SampleRepositoryCustom {
	Sample findByData(String data);
	List<Sample> findAll();
}
