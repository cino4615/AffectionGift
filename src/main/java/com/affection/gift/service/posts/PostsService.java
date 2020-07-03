package com.affection.gift.service.posts;

import com.affection.gift.domain.posts.Posts;
import com.affection.gift.domain.posts.PostsRepository;
import com.affection.gift.web.dto.PostsResponseDto;
import com.affection.gift.web.dto.PostsSaveRequestDto;
import com.affection.gift.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//@RequiredArgsConstructor
//final이 붙은 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성해줌
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                        .orElseThrow(()->
                             new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
                        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}