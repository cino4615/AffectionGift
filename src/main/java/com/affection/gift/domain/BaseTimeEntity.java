package com.affection.gift.domain;


//해당 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의
//createdDate, modifiedDate를 자동으로 관리하는 역할
//이클래스만 상속받으면 더이상 등록일/수정일로 고민할 일 없음

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//@MappedSuperclass
//JPA Entity 클래스들의 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 함

//@EntityListeners(AuditingEntityListener.class)
//BaseTimeEntity 클래스에 Auditing(감사) 기능을 포함시킨다
//생성일 수정일 자동화
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    
    //Entity가 생성되어 저장될 떄 시간이 자동 저장됨
    @CreatedDate
    private LocalDateTime createdDate;

    //조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
