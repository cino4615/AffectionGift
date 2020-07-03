package com.affection.gift.web;

import com.affection.gift.service.posts.PostsService;
import com.affection.gift.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//페이지에 관련된 컨트롤러는 모두 IndexController에서 처리
@RequiredArgsConstructor
@Controller
public class IndexController {
    //머스테치 덕분에 컨트롤러에서 문자열을 반환할 때
    //앞의 경로와 뒤의 파일 확장자는 자동으로 지정

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }


}
