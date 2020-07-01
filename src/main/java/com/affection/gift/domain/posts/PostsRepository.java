package com.affection.gift.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//DB Layer접근자
//JpaRepository<Entity클래스, PK타입>을 상속 받으면 기본적인 CRUD 메소드가 자동으로 생성
//@Repository를 추가할 필요도 없음
//주의할점 : Entity클래스와 Entity Repository는 함께 위치해야 한다
//Entity클래스는 기본 Repository 없이는 제대로 역할을 할 수 없음
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
