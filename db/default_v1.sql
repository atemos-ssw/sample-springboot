-- 내 스키마
DROP SCHEMA IF EXISTS `MY_SCHEMA`;

-- 내 스키마
CREATE SCHEMA `MY_SCHEMA`;

-- 전력미터기
CREATE TABLE `MY_SCHEMA`.`METER` (
	`METER_ID`   INT         NOT NULL COMMENT '미터아이디', -- 미터아이디
	`METER_TYPE` VARCHAR(4)  NULL     COMMENT '미터기타입', -- 미터기타입
	`STATUS`     BOOL        NULL     COMMENT '미터기 현재상태', -- 미터기 현재상태
	`PERIOD`     INT         NULL     COMMENT '검침주기', -- 검침주기
	`METER_NAME` VARCHAR(20) NULL     COMMENT '미터기명(설비명도 될수있음)' -- 미터기명(설비명도 될수있음)
)
COMMENT '전력미터기';

-- 전력미터기
ALTER TABLE `MY_SCHEMA`.`METER`
	ADD CONSTRAINT `PK_METER` -- 전력미터기 기본키
	PRIMARY KEY (
	`METER_ID` -- 미터아이디
	);

-- 고객
CREATE TABLE `MY_SCHEMA`.`CUSTOMER` (
	`CUSTOMER_ID`    INT         NOT NULL COMMENT '고객아이디', -- 고객아이디
	`SERVER_ID`      INT         NULL     COMMENT '서버ID', -- 서버ID
	`NAME`           VARCHAR(20) NULL     COMMENT '고객이름', -- 고객이름
	`IS_AGREE`       BOOL        NULL     COMMENT '고객동의여부', -- 고객동의여부
	`TEL`            VARCHAR(12) NULL     COMMENT '고객연락처', -- 고객연락처
	`ENERGY_TYPE`    VARCHAR(4)  NULL     COMMENT '에너지종', -- 에너지종
	`CONTRACT_AMOUT` INT         NULL     COMMENT '전기계약용량', -- 전기계약용량
	`COST_TYPE`      VARCHAR(4)  NULL     COMMENT '전기요금제', -- 전기요금제
	`METER_ID`       INT         NULL     COMMENT '미터아이디', -- 미터아이디
	`BUILDING_ID`    INT         NULL     COMMENT '건물아이디' -- 건물아이디
)
COMMENT '고객';

-- 고객
ALTER TABLE `MY_SCHEMA`.`CUSTOMER`
	ADD CONSTRAINT `PK_CUSTOMER` -- 고객 기본키
	PRIMARY KEY (
	`CUSTOMER_ID` -- 고객아이디
	);

-- 지역
CREATE TABLE `MY_SCHEMA`.`LOCATION` (
	`LOCATION_ID` INT         NOT NULL COMMENT '지역아이디', -- 지역아이디
	`NAME`        VARCHAR(20) NULL     COMMENT '지역명' -- 지역명
)
COMMENT '지역';

-- 지역
ALTER TABLE `MY_SCHEMA`.`LOCATION`
	ADD CONSTRAINT `PK_LOCATION` -- 지역 기본키
	PRIMARY KEY (
	`LOCATION_ID` -- 지역아이디
	);

-- 전기 검침정보
CREATE TABLE `MY_SCHEMA`.`METER_READING` (
	`M_YEAR`      INT        NOT NULL COMMENT '계량년도', -- 계량년도
	`M_MONTH`     INT        NOT NULL COMMENT '계량월', -- 계량월
	`M_DAY`       INT        NOT NULL COMMENT '계량일', -- 계량일
	`M_TIME`      INT        NOT NULL COMMENT '시간', -- 시간
	`M_MINUTE`    INT        NOT NULL COMMENT '분', -- 분
	`M_TIMESTAMP` TIMESTAMP  NOT NULL COMMENT '검침시간', -- 검침시간
	`METER_ID`    INT        NOT NULL COMMENT '미터아이디', -- 미터아이디
	`CUSTOMER_ID` INT        NULL     COMMENT '고객아이디', -- 고객아이디
	`MR_SCS_YN`   VARCHAR(1) NULL     COMMENT '수집성공여부', -- 수집성공여부
	`Voltage_A`   FLOAT      NULL     COMMENT 'A상 전압', -- A상 전압
	`Voltage_B`   FLOAT      NULL     COMMENT 'B상 전압', -- B상 전압
	`Voltage_C`   FLOAT      NULL     COMMENT 'C상 전압', -- C상 전압
	`Ampare_A`    FLOAT      NULL     COMMENT 'A상 전류', -- A상 전류
	`Ampare_B`    FLOAT      NULL     COMMENT 'B상 전류', -- B상 전류
	`Ampare_C`    FLOAT      NULL     COMMENT 'C상 전류', -- C상 전류
	`Voltage`     FLOAT      NULL     COMMENT '전압', -- 전압
	`Ampare`      FLOAT      NULL     COMMENT '전류', -- 전류
	`PF_A`        FLOAT      NULL     COMMENT 'A상 역율', -- A상 역율
	`PF_B`        FLOAT      NULL     COMMENT 'B상 역율', -- B상 역율
	`PF_C`        FLOAT      NULL     COMMENT 'C상 역율', -- C상 역율
	`PF`          FLOAT      NULL     COMMENT '역율', -- 역율
	`Hz`          FLOAT      NULL     COMMENT '주파수', -- 주파수
	`KWH`         FLOAT      NULL     COMMENT '전력량' -- 전력량
)
COMMENT '전기 검침정보';

-- 전기 검침정보
ALTER TABLE `MY_SCHEMA`.`METER_READING`
	ADD CONSTRAINT `PK_METER_READING` -- 전기 검침정보 기본키
	PRIMARY KEY (
	`M_YEAR`,      -- 계량년도
	`M_MONTH`,     -- 계량월
	`M_DAY`,       -- 계량일
	`M_TIME`,      -- 시간
	`M_MINUTE`,    -- 분
	`M_TIMESTAMP`, -- 검침시간
	`METER_ID`     -- 미터아이디
	);

-- AMR서버정보
CREATE TABLE `MY_SCHEMA`.`AMR_INFO` (
	`SERVER_ID` INT         NOT NULL COMMENT '서버ID', -- 서버ID
	`ADDRESS`   VARCHAR(20) NULL     COMMENT '접속주소', -- 접속주소
	`PORT`      INT         NULL     COMMENT '접속포트', -- 접속포트
	`STATUS`    BOOL        NULL     COMMENT '현재 접속상태', -- 현재 접속상태
	`URL`       VARCHAR(20) NULL     COMMENT '접속 URL' -- 접속 URL
)
COMMENT 'AMR서버정보';

-- AMR서버정보
ALTER TABLE `MY_SCHEMA`.`AMR_INFO`
	ADD CONSTRAINT `PK_AMR_INFO` -- AMR서버정보 기본키
	PRIMARY KEY (
	`SERVER_ID` -- 서버ID
	);

-- 알람설정값
CREATE TABLE `MY_SCHEMA`.`ALARM` (
	`CUSTOMER_ID` INT  NOT NULL COMMENT '고객아이디', -- 고객아이디
	`IS_APPLY`    BOOL NULL     COMMENT '알람처리여부', -- 알람처리여부
	`KWH_LIMIT`   INT  NULL     COMMENT '전력사용량 상한', -- 전력사용량 상한
	`COST_LIMIT`  INT  NULL     COMMENT '요금사용량 상한' -- 요금사용량 상한
)
COMMENT '알람설정값';

-- 알람설정값
ALTER TABLE `MY_SCHEMA`.`ALARM`
	ADD CONSTRAINT `PK_ALARM` -- 알람설정값 기본키
	PRIMARY KEY (
	`CUSTOMER_ID` -- 고객아이디
	);

-- 월 고객 에너지 사용 요금
CREATE TABLE `MY_SCHEMA`.`MONTH_USER_USAGE_COST` (
	`CUSTOMER_ID` INT   NOT NULL COMMENT '고객아이디', -- 고객아이디
	`YEAR`        INT   NOT NULL COMMENT '년도', -- 년도
	`MONTH`       INT   NOT NULL COMMENT '월', -- 월
	`COST`        INT   NULL     COMMENT '전기요금', -- 전기요금
	`KWH`         FLOAT NULL     COMMENT '사용량' -- 사용량
)
COMMENT '월 고객 에너지 사용 요금';

-- 월 고객 에너지 사용 요금
ALTER TABLE `MY_SCHEMA`.`MONTH_USER_USAGE_COST`
	ADD CONSTRAINT `PK_MONTH_USER_USAGE_COST` -- 월 고객 에너지 사용 요금 기본키
	PRIMARY KEY (
	`CUSTOMER_ID`, -- 고객아이디
	`YEAR`,        -- 년도
	`MONTH`        -- 월
	);

-- 건물정보
CREATE TABLE `MY_SCHEMA`.`BUILDING` (
	`BUILDING_ID` INT         NOT NULL COMMENT '건물아이디', -- 건물아이디
	`ADDRESS`     VARCHAR(20) NULL     COMMENT '건물주소', -- 건물주소
	`NAME`        VARCHAR(20) NULL     COMMENT '건물명', -- 건물명
	`TEL`         VARCHAR(12) NULL     COMMENT '건물주연락처', -- 건물주연락처
	`LOCATION_ID` INT         NULL     COMMENT '지역아이디' -- 지역아이디
)
COMMENT '건물정보';

-- 건물정보
ALTER TABLE `MY_SCHEMA`.`BUILDING`
	ADD CONSTRAINT `PK_BUILDING` -- 건물정보 기본키
	PRIMARY KEY (
	`BUILDING_ID` -- 건물아이디
	);

-- 월 설비 에너지 사용 요금
CREATE TABLE `MY_SCHEMA`.`MONTH_EQUIP_USAGE_COST` (
	`YEAR`     INT   NULL COMMENT '년도', -- 년도
	`METER_ID` INT   NULL COMMENT '미터아이디', -- 미터아이디
	`MONTH`    INT   NULL COMMENT '월', -- 월
	`COST`     INT   NULL COMMENT '전기요금', -- 전기요금
	`KWH`      FLOAT NULL COMMENT '사용량' -- 사용량
)
COMMENT '월 설비 에너지 사용 요금';

-- 고객
ALTER TABLE `MY_SCHEMA`.`CUSTOMER`
	ADD CONSTRAINT `FK_AMR_INFO_TO_CUSTOMER` -- AMR서버정보 -> 고객
	FOREIGN KEY (
	`SERVER_ID` -- 서버ID
	)
	REFERENCES `MY_SCHEMA`.`AMR_INFO` ( -- AMR서버정보
	`SERVER_ID` -- 서버ID
	);

-- 고객
ALTER TABLE `MY_SCHEMA`.`CUSTOMER`
	ADD CONSTRAINT `FK_METER_TO_CUSTOMER` -- 전력미터기 -> 고객
	FOREIGN KEY (
	`METER_ID` -- 미터아이디
	)
	REFERENCES `MY_SCHEMA`.`METER` ( -- 전력미터기
	`METER_ID` -- 미터아이디
	);

-- 고객
ALTER TABLE `MY_SCHEMA`.`CUSTOMER`
	ADD CONSTRAINT `FK_BUILDING_TO_CUSTOMER` -- 건물정보 -> 고객
	FOREIGN KEY (
	`BUILDING_ID` -- 건물아이디
	)
	REFERENCES `MY_SCHEMA`.`BUILDING` ( -- 건물정보
	`BUILDING_ID` -- 건물아이디
	);

-- 전기 검침정보
ALTER TABLE `MY_SCHEMA`.`METER_READING`
	ADD CONSTRAINT `FK_METER_TO_METER_READING` -- 전력미터기 -> 전기 검침정보
	FOREIGN KEY (
	`METER_ID` -- 미터아이디
	)
	REFERENCES `MY_SCHEMA`.`METER` ( -- 전력미터기
	`METER_ID` -- 미터아이디
	);

-- 전기 검침정보
ALTER TABLE `MY_SCHEMA`.`METER_READING`
	ADD CONSTRAINT `FK_CUSTOMER_TO_METER_READING` -- 고객 -> 전기 검침정보
	FOREIGN KEY (
	`CUSTOMER_ID` -- 고객아이디
	)
	REFERENCES `MY_SCHEMA`.`CUSTOMER` ( -- 고객
	`CUSTOMER_ID` -- 고객아이디
	);

-- 알람설정값
ALTER TABLE `MY_SCHEMA`.`ALARM`
	ADD CONSTRAINT `FK_CUSTOMER_TO_ALARM` -- 고객 -> 알람설정값
	FOREIGN KEY (
	`CUSTOMER_ID` -- 고객아이디
	)
	REFERENCES `MY_SCHEMA`.`CUSTOMER` ( -- 고객
	`CUSTOMER_ID` -- 고객아이디
	);

-- 월 고객 에너지 사용 요금
ALTER TABLE `MY_SCHEMA`.`MONTH_USER_USAGE_COST`
	ADD CONSTRAINT `FK_CUSTOMER_TO_MONTH_USER_USAGE_COST` -- 고객 -> 월 고객 에너지 사용 요금
	FOREIGN KEY (
	`CUSTOMER_ID` -- 고객아이디
	)
	REFERENCES `MY_SCHEMA`.`CUSTOMER` ( -- 고객
	`CUSTOMER_ID` -- 고객아이디
	);

-- 건물정보
ALTER TABLE `MY_SCHEMA`.`BUILDING`
	ADD CONSTRAINT `FK_LOCATION_TO_BUILDING` -- 지역 -> 건물정보
	FOREIGN KEY (
	`LOCATION_ID` -- 지역아이디
	)
	REFERENCES `MY_SCHEMA`.`LOCATION` ( -- 지역
	`LOCATION_ID` -- 지역아이디
	);

-- 월 설비 에너지 사용 요금
ALTER TABLE `MY_SCHEMA`.`MONTH_EQUIP_USAGE_COST`
	ADD CONSTRAINT `FK_METER_TO_MONTH_EQUIP_USAGE_COST` -- 전력미터기 -> 월 설비 에너지 사용 요금
	FOREIGN KEY (
	`METER_ID` -- 미터아이디
	)
	REFERENCES `MY_SCHEMA`.`METER` ( -- 전력미터기
	`METER_ID` -- 미터아이디
	);