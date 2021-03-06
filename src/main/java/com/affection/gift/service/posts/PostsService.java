package com.affection.gift.service.posts;

import com.affection.gift.domain.posts.Posts;
import com.affection.gift.domain.posts.PostsRepository;
import com.affection.gift.web.dto.PostsListResponseDto;
import com.affection.gift.web.dto.PostsResponseDto;
import com.affection.gift.web.dto.PostsSaveRequestDto;
import com.affection.gift.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //스프링의 트랜잭션 처리방법 중 어노테이션 방식으로 처리하는, 선언적 트랜잭션
    //@Transaction을 적어주면 이 클래스에 트랜잭션 기능이 적용된 프록시 객체가 생성됨
    //해당 어노테이션이 포함된 메소드가 호출될 경우 PlatformTransactionManager를 사용하여
    //트랜잭션을 시작하고, 정상 여부에 따라 Rollback 또는 Commit 한다
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    //.orElseThrow()
    //라이브러리 메서드가 반환할 결과값이 ‘없음’을 명백하게 표현할 필요가 있는 곳에서
    // 제한적으로 사용할 수 있는 메커니즘을 제공하는 것이 Optional을 만든 의도였다.
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->
                        new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    //readOnly = true속성
    //트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선
    //등록/수정/삭제 기능이 전혀 없는 서비스 메소드에서 사용 추천
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                //.map(posts -> new PostsListResponseDto(posts))와 같은 의미
                //postsRepository 결과로 넘어온 Posts의 Stream을
                //map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        //Jparepository에서 이미 delete 메소드를 지원하고 있으니 이를 사용
        //엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있음
        //존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제
        postsRepository.delete(posts);
    }


}
