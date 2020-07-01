package com.affection.gift.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith
//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
//SpringRunner라는 스프링 실행자를 사용
//즉, 스프링 부트 테스트와 JUnit사이의 연결자 역할을 한다

//@WebMvcTest
//여러 스프링의 테스트 어노테이션 중, Web(Spring MVC)에 집중 할 수 있는 어노테이션
//선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
//단, @Service, @Component, @Repository 등은 사용할 수 없다.
//여기서는 컨트롤러만 사용하기 때문에 선언함
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //웹 API를 테스트할 때 사용
    //스프링 MVC테스트의 시작점이다
    //이 클래스를 통해 HTTP GET, POST에 대한 API 테스트 가능
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다
        //체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어선 선언가능
        //-------------------------------------------------------
        //mvc.perform의 결과를 검증, HTTP Header의 Status를 검증
        //우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증한다
        //-------------------------------------------------------
        //mvc.perform의 결과를 검증, 응답 본문 내용을 검증
        //Controller에서 "hello"를 리턴하기 떄문에 이 값이 맞는지 검증
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;
        //.param
        // api 테스트 시 사용될 요청 파라미터 설정
        //값은 String만 허용, 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야함
        
        //jsonPath
        //JSON 응답값을 필드별로 검증할 수 있는 메소드
        //$를 기준으로 필드명을 명시
        //여기서는 name과 amount를 검증하니 $.name, $.amount로 검증한다
        mvc.perform(
                get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.amount", is(amount)));
    }

}
