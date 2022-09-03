package com.fastcampus.jpa.bookmanager.domain;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEnitityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
/*
* DB테이블 매핑 @Entity가 붙은 클래스는 JPA가 관리함
* 주의사항
* 1. 기본 생성자는 필수(JPA Entity 객체생성시 기본생성자를 사용)
* 2. final 클래스 enum, interface, inner class에서는 사용불가
* 3. 저장할 필드는 final 불가
*/
@Entity

/*
* Entity와 매핑할 테이블을 지정 생략시 매핑한 Entity이므로 테이블이름으로 사용
* @Table에서 name은 말 그래도 테이블 이름, indexes={@Index(columnList="해당필드(해당컬럼)")을 하면 해당테이블에 index가 걸린다. }
* 그렇지만 indexes는 왠만하면 DB단에서 작업을 한다. 여기서 Index를 걸어도 crud할떄 index가 적용되지 않는경우도 있다고 한다.
*/
//@Table(name = "user", indexes = {@Index(columnList = "name")})
@EntityListeners(value = {UserEnitityListener.class})
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;
    
    /*Enum을 사용할 때는 @Enumerated(value = EnumType.STRING)을 사용해야 제대로 동작할 수 있음*/
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    /*@Column에는 insertable, updatable이 잇는데 해당 값을 true로 변경하면 insert, update 작업시 해당 필드에 해당하는 칼럼은 작업하지 않는다.*/
//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
    
    /*@Transient은 table 생성 및 삭제 데이터 CRUD를 할 때 해당되지 않게 하는 어노테이션*/
    @Transient
    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

    /*Entitiy Listener의 종류*/
    /* Listenser는 이벤트가 발생하면 그 이벤트가 발생하기전에 실행하는 메소드들*/
    /*
    @PrePersist : insert 메소드가 호출 되기전에 작동
    @PreUpdate : Merge 메소드가 호출 되기전에 작동
    @PreRemove Delete 메소드가 호출 되기전에 작동
    @PostPersist : insert 메소드가 호출 된 후에 작동
    @PostUpdate : Merge 메소드가 호출 된 후에 작동
    @PostRemove : Delete 메소드가 호출 된 후에 작동
    @PostLoad : select 조회직 후에 작동
     */

//    @PrePersist
//    public void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void PreUpdate(){
//        this.updatedAt = LocalDateTime.now();
//    }

}
