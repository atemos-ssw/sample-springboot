package com.atemos.sample;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atemos.sample.entity.Sample;
import com.atemos.sample.repository.SampleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Disabled
public class SampleRepositoryTest {

	@Autowired
	private SampleRepository sampleRepository;

	@Autowired
	EntityManager entityManager;

	@BeforeEach
	void init() {
		setUp();
	}

	private void setUp() {
		// TODO Auto-generated method stub
		String data;
		Sample sample;
		for(int i=0; i<10;i++) {
			sample = Sample.builder().data("data_"+i).build();
			sampleRepository.save(sample);
		}
		
		entityManager.flush();
		entityManager.clear();
	}
	
	
	@Transactional
	@DisplayName("fetch Sample data")
	@Test
	void testGetData() {
//		Sample sample = Sample.builder()
		List<Sample> samples = sampleRepository.findAll();
		log.debug("samples:[{}]", samples);
		assertThat(!samples.isEmpty());
	}
}
