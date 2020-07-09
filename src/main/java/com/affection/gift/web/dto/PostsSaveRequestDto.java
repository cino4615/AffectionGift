package com.affection.gift.web.dto;

import com.affection.gift.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//결과값으로 여러 테이블을 조인해서 줘야할 경우가 빈번하므로 Entity클래스 만으로 표현하기 어려움
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // toEntity 메소드 호출 시 위에서 생성한 빌더패턴으로 생성된 엔티티를 넘겨줌
    // 빌더 패턴의 장점 : 어떤 매개변수에 어떤 값이 들어갔는지 직관적으로 알수있음
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
