package com.affection.gift.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//Enum class :
//서로 관련있는 상수들끼리 모아 대표할 수 있는 이름으로 타입을 정의하는 것
@Getter
@RequiredArgsConstructor
public enum Role {

    //스프링 시큐리티에서는 권한코드에 항상 ROLE_이 앞에 있어야만 한다
    GUEST("ROLE_GUEST", "손님"), USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
