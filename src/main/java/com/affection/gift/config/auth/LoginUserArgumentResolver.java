package com.affection.gift.config.auth;

import com.affection.gift.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

//HandlerMethodArgumentResolver
//컨트롤러 메서드에서 특정 조건에 맞는 파라미터가 있을 때 원하는 값을 바인딩해주는 인터페이스입니다.
//@RequestBody , @PathVariable을 사용해 값을 받아올 때 이용됨

//HandlerMethodArgumentResolver 인터페이스를 구현한 클래스
//해당 인터페이스는 조건에 맞는 경우 메소드가 있다면 HandlerMethodArgumentResolver의
//구현체가 지정한 갓으로 해당 메소드의 파라미터를 넘길 수 있음
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver  implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    //supportsParameter
    //컨트롤러 메서드의 특정 파라미터를 지원하는지 판단(파라미터의 조건 성립하는지 true/false로 반환)
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //파라미터에 @LoginUser 어노테이션이 붙어있는지(LoginUser클래스 타입을 가진 어노테이션이 not null일 떄)
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        //파라미터 클래스 타입이 SessionUser.class인지
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        //둘다 성립해야 true 하나라도 성립하지 않으면 false
        return isLoginUserAnnotation && isUserClass;
    }

    //resolveArgument : 실제로 바인딩할 객체 리턴(파라미터에 전달할 객체를 생성)
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}

//HandlerMethodArgumentResolver를 상속받은 객체는 두가지 메소드를 구현해야한다.
//supportsParameter 메서드는 현재 파라미터를 resolver이 지원하는지에 대한 boolean을 리턴합니다.
//resolveArgument 메서드는 실제로 바인딩을 할 객체를 리턴합니다.