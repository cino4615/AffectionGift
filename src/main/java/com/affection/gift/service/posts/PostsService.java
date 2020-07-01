package com.affection.gift.service.posts;

import com.affection.gift.domain.posts.PostsRepository;
import com.affection.gift.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
//final이 붙은 모든 필드를 인자값으로 하는 생성자를 롬복이 대신 생성해줌
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
