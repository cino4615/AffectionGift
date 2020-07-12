package com.affection.gift.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

//ProfileController나 Environment모두 자바클래스(인터페이스)이기 때문에 쉽게 테스트가능
//Environment는 인터페이스라 가짜 구현체인 MockEnvironment(스프링에서 제공)을 사용해 테스트
//생성자 DI의 유용성 ㅇ르 알 수 있다.
//만약 Environment를 @Autowired로 DI 받았다면 이러한 테스트 코드 작성 불가능
public class ProfileControllerUnitTest {

    @Test
    public void real_profile이_조회된다(){
        //given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile이_없으면_첫번째가_조회된다(){
        ///given
        String expectedprofile = "oauth";
        MockEnvironment env = new MockEnvironment();

        env.addActiveProfile(expectedprofile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedprofile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다(){
        //given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

}
