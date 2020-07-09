package com.affection.gift.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//DB Layer접근자
//JpaRepository<Entity클래스, PK타입>을 상속 받으면 기본적인 CRUD 메소드가 자동으로 생성
//@Repository를 추가할 필요도 없음
//주의할점 : Entity클래스와 Entity Repository는 함께 위치해야 한다
//Entity클래스는 기본 Repository 없이는 제대로 역할을 할 수 없음
public interface PostsRepository extends JpaRepository<Posts, Long> {

    //규모가 있는 프로젝트의 경우 데이터 조회는 PK조인, 복잡한 조건으로 인해
    //Entity클래스 만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용하기도함, Mybatis, Jooq, Querydsl(추천)
    //조회는 조회용 프레임워크를 사용하고, 등록/수정/삭제는 SpringDataJpa를 통해 진행

    //@Query
    //SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성가능
    //밑의 쿼리는 SpringDataJpa에서 제공하는 기본메소드로 작성가능하지만 가독성은 @Query가 더 좋다
    //SELECT p FROM Posts p에서 Posts에 p라는 별칭을 준 것, JPQL에서 엔티티의 별칭은 필수적으로 명시해야 한다
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
