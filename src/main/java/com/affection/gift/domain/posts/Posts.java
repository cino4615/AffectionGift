package com.affection.gift.domain.posts;

import com.affection.gift.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//롬복
@Getter
@NoArgsConstructor
//JPA
//@Entity
//테이블과 링크될 클래스임을 나타냄
// 기본값으로 클래스의 카멜이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
//ex)SalesManager.java -> sales_manager table
@Entity
//Posts클래스는 실제 DB테이블과 매칭될 클래스(Entity 클래스)
//Entity클래스에는 절대 Setter메소드를 만들지 않음
//대신, 해당 필드의 값 변경이 필요하면 반드시 목적과 의도를 나타낼 수 있는 메소드 추가
public class Posts extends BaseTimeEntity {
    //@Id
    //해당 테이블의 PK필드를 나타낸다
    @Id
    //@GeneratedValue
    //PK의 생성규칙을 나타냄
    //스프링 부트 2.0에서는 GenerationType.IDENTITY옵션을 추가해야만 auto_increment가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //웬만하면 Entity의 PK는 Long타입의 Auto_increment추천(bigint타입이 됨)

    //@Column
    //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨
    //사용하는 이유는, 기본값 외에 추가 변경이 필요한 옵션이 있으면 사용
    //문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex.title)
    //타입을 TEXT로 변경하고 싶거나(ex.content) 등의 경우에 사용됨
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //@Builder
    //해당 클래스의 빌더 패턴 클래스를 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    //빌더 패턴은 어느 필드에 어떤 값을 넣어야 할지 명확히 인지 할 수 있음
    //이 빌더 패턴을 통해 생성하며 Setter는 존재하지 않음
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
