package com.atemos.sample;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.atemos.sample.entity.Hello;
import com.atemos.sample.entity.QHello;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@SpringBootTest
class DemoApplicationTests {

	@PersistenceContext
	EntityManager em;
	
	@Test
	void contextLoads() {
		Hello hello = new Hello("1");
		
		em.persist(hello);
		em.flush();
		em.clear();
		
		
		QHello qHello = QHello.hello;
		JPAQueryFactory factory = new JPAQueryFactory(em);
		
		Hello data = factory.selectFrom(qHello).fetchOne();
		assertThat(data.getName()).isEqualTo("1");
	}

}
