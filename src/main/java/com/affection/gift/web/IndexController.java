package com.affection.gift.web;

import com.affection.gift.config.auth.LoginUser;
import com.affection.gift.config.auth.dto.SessionUser;
import com.affection.gift.service.posts.PostsService;
import com.affection.gift.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

//페이지에 관련된 컨트롤러는 모두 IndexController에서 처리
@RequiredArgsConstructor
@Controller
public class IndexController {
    //머스테치 덕분에 컨트롤러에서 문자열을 반환할 때
    //앞의 경로와 뒤의 파일 확장자는 자동으로 지정

    private final PostsService postsService;
    private final HttpSession httpSession;

    //@LoginUser SessionUser user
    //기존에 httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨
    //이제는 어느 컨트롤러에든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 됨
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //세션에 저장된 값이 있을 때만 model에 userName을 등록
        //세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인버튼이 보이게됨
        if(user != null){
            model.addAttribute("myName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
    //@PathVariable에 적용된 동일한 이름을 갖는 파라미터와 매핑됨
    //URL의 정의 부분과 메소드의 파라미터 부분에 정의를 하여 사용함
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);

        model.addAttribute("post", dto);
        //posts-update.mustache
        return "posts-update";
    }

}
