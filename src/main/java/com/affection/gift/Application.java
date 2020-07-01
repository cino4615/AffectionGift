package com.affection.gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//SpringBootApplication이 있는 위치를 기준으로 읽어가기 때문에 항상 프로젝트의 최상단에 위치
//스프링 부트의 자동설정, 스프링 Bean읽기와 생성을 모두 자동으로 설정
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //SpringApplication.run 으로 내장 WAS(외부 WAS를 두지않고)를 실행
        //내장 WAS를 권장하는 이유 : 언제 어디서나 같은 환경에서 스프링부트를 배포할 수 있기 때문
        SpringApplication.run(Application.class, args);
    }
}
