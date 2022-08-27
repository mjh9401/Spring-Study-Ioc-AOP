package com.fastcampus.jpa.bookmanager.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
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
@Table(name = "user", indexes = {@Index(columnList = "name")})
public class User {
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
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    /*@Transient은 table 생성 및 삭제 데이터 CRUD를 할 때 해당되지 않게 하는 어노테이션*/
    @Transient
    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
}
