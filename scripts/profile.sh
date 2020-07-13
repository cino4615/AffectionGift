#!/urs/bin/env bash

# 쉬고 있는 profile 찾기 : real1이 사용중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음

function find_idle_profile(){
    #현재 엔진엑스가 바라보고 있는 스플이 부트가 정상적으로 수행중인지 확인
    #응답값을 HttpStatus로 받습니다
    #정상이면 200, 오류가 발생하면 400~503 사이로 발생하니 400이상은 모두 예외로 보고
    #real2를 현재 profile로 사용
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] #400보다 크면 (즉, 40x/50x ㅔ얼 모두 포함)
    then
        CURRENT_PROFILE=real2
    else
        CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
        IDLE_PROFILE=real2
    else
        IDLE_PROFILE=real1
    fi
    # bash라는 스크립트는 값을 반환하는 기능이 없음
    #그래서 제일 마지막 줄에 echo로 결과 출력 후, 클라이언트에서 그 값을 잡아서
    #($(find_idle_profile)) 사용합니다.
    #중간에 echo를 사용해서는 안됨
    echo "${IDLE_PROFILE}"
}

#쉬고 있는 profile의 port찾기
function find_idle_port(){
    #엔진엑스와 연결되어 있지 않은 profile입니다
    #스프링 부트 프로젝트르르 이 profile로 연결하기 위해 반환
    IDLE_PROFILE=${find_idle_profile}

    if [ ${IDLE_PROFILE} == real1 ]
    then
        echo "8081"
    else
        echo "8082"
    fi

}
