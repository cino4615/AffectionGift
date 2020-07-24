package com.affection.gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication
//SpringBootApplication이 있는 위치를 기준으로 읽어가기 때문에 항상 프로젝트의 최상단에 위치
//스프링 부트의 자동설정, 스프링 Bean읽기와 생성을 모두 자동으로 설정
//@Configuration @ComponentScan @EnableAutoConfiguration 세개가 합쳐진 것과 같다
//Application.java 자체를 Config파일로 인식시키고,
//패키지 내 모든 Component를 스캔하며,
//필요한 자동 설정을 한다

// @EnableJpaAuditing
// JPA Auditing 어노테이션들을 모두 활성화 할 수 있도록 추가하는 어노테이션
@SpringBootApplication
//SpringBootApplication과 EnableJpaAuditing을 분리, JpaConfig로 이동
//@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        //SpringApplication.run 으로 내장 WAS(외부 WAS를 두지않고)를 실행
        //내장 WAS를 권장하는 이유 : 언제 어디서나 같은 환경에서 스프링부트를 배포할 수 있기 때문
        SpringApplication.run(Application.class, args);
    }
}
