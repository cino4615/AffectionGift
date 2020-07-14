package com.affection.gift.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    // @Enumerated(EnumType.STRING)
    //Enum클래스를 엔티티클래스의 속성으로 지정할 때 사용하는 어노테이션

    //(EnumType.ORDINAL), (EnumType.STRING)
    //JPA로 데이터베이스로 저장할 때 Enum값을 어떤 형태로 저장할지를 결정
    //기본적으로는 int로 된 숫자가 저장
    //숫자로 저장되면 그 값이 무슨 코드를 의미하는지 알 수 없으므로, 문자열로 설정
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    //key : ROLE_GUEST, ROLE_USER / title : 손님, 일반사용자
    public String getRoleKey(){
        return this.role.getKey();
    }

}
