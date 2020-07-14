package com.affection.gift.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    //실제로 URL호출 시 페이지의 내용이 제대로 호출되는지에 대한 테스트
    //"/"로 호출 했을 때 "스프링 부트로 시작하는 웹 서비스" 문자열이 포함되어 있는지
    @Test
    public void 메인페이지_로딩(){
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("강수현이예진안희진 바 보");
    }
}
