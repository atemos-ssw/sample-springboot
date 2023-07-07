#REST API 사용법

## Get Site 

> ex) curl --location 'http://localhost:8080/api/site/128?key=AIzaSyB5FJ2MDKKqK8JVWU21HOFa35Jqzt1U40Y&logicalId=0c1bf0Ao2s&siteId=128&items=average_phase_current&groupId=1'

## Get AC Feeder 


# JPA + QueryDSL Concept

> https://velog.io/@soyeon207/QueryDSL-Spring-Boot-%EC%97%90%EC%84%9C-QueryDSL-JPA-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0

## QClass 만드는법
> https://lts0606.tistory.com/640
>	> QClass 만들기 : run mavn generate-source

# Test

> https://tychejin.tistory.com/415

# Quartz

> https://www.tutorialsbuddy.com/quartz-with-spring-boot-and-mysql-example
> https://pingfanzhilu.tistory.com/entry/Spring-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8Spring-Boot-Quartz-Scheduler-%EC%82%AC%EC%9A%A9%EB%B2%95

> https://pooney.tistory.com/99 #db 연동
> https://co-de.tistory.com/12 #동적 트리거
# JIB Build 

>현재 대몬에 올리는 방법

>	>mvn compile jib:dockerBuild(build -> jar -> docker image -> 현재 docker deamon)

>docker image 파일로 저장하는 방법

>	>mvn compile jib:buildTar(ave your image to disk as a tarball)

# 빌드 타입(local, devel, prod)

> java -jar -P {local, dev, prod} 

> https://wildeveloperetrain.tistory.com/8

# 멀티 DB 스키마 접근 방법
> https://minaminaworld.tistory.com/219