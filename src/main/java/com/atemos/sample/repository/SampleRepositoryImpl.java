package com.atemos.sample.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.atemos.sample.entity.QSample;
import com.atemos.sample.entity.Sample;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Sample findByData(String data) {
		// TODO Auto-generated method stub
		QSample qsample = QSample.sample;
		
		return queryFactory.selectFrom(qsample).where(qsample.data.eq(data)).fetchOne();
	}

	@Override
	public List<Sample> findAll() {
		// TODO Auto-generated method stub

		QSample qsample = QSample.sample;
		List<Sample> list = queryFactory.selectFrom(qsample).fetch();
		return list != null? list : Collections.emptyList();
	 
	}

}
