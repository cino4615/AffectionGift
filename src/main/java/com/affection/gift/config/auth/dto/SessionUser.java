package com.affection.gift.config.auth.dto;

import com.affection.gift.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//SessionUser에는 인증된 사용자 정보만 필요하므로 name, email, picture만 선언

//왜 User클래스를 사용하면 안되는가?
//User클래스에는 직렬화 코드를 구현하지 않았기 때문
//User클래스에 직렬화 코드를 넣는다면 운영, 유지보수에 불편
//User클래스는 엔티티이기 때문에 언제 다른 엔티티와 관곅 형성될지 모르기 때문
//User클래스가 자식 엔티티를 갖고 있다면, 직렬화 대상에 자식까지 포함되니 성능문제가 발생할 확률 높음
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
