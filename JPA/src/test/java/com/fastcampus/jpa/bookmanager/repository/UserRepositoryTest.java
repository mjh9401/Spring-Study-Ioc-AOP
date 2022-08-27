package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;


@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() { // create, read, update, delete
        userRepository.save(new User("david","dvid@naver.com"));
        // insert와 update의 차이는 pk가 존재여부에 따라 save 메소드가 insert or update가 된다.
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("asdf@gmail.com");
        userRepository.save(user);

    }

    @Test
    void select(){
        System.out.println(userRepository.findByName("dennis"));

        /**
         * findBy, getBy, readBy, queryBy, searchBy, streamBy, find..By는
         * 해당 파라미터를 where절에 넣어서 해당 하는 값을 가져오는 select절을 실행한다.
         */
        System.out.println("findByEmail: "+userRepository.findByEmail("martin@fastcampus.com"));
        System.out.println("getByEmail: "+userRepository.getByEmail("martin@fastcampus.com"));
        System.out.println("readByEmail: "+userRepository.readByEmail("martin@fastcampus.com"));
        System.out.println("queryByEmail: "+userRepository.queryByEmail("martin@fastcampus.com"));
        System.out.println("searchByEmail: "+userRepository.searchByEmail("martin@fastcampus.com"));
        System.out.println("streamByEmail: "+userRepository.streamByEmail("martin@fastcampus.com"));
        System.out.println("findUserByEmail: "+userRepository.findUserByEmail("martin@fastcampus.com"));

        System.out.println("findSomethingByEmail: "+userRepository.findSomethingByEmail("martin@fastcampus.com"));

        /**
         *  select 키워드 + Top+ 숫자 또는 select 키워드 + First+ 숫자 : select 결과의 숫자의 만큼만 리턴한다.
         *  ex) Top2 or First 2 => select 결과의 상위 2개만 리턴한다.
         *  Last는 무시가 되어 query상으로는 limit가 사라진다.
         */
        System.out.println("findTop2ByName: "+userRepository.findTop2ByName("martin"));
        System.out.println("findFirst2ByName: "+userRepository.findFirst2ByName("martin"));
        System.out.println("findLast1ByName: "+userRepository.findLast1ByName("martin"));
        /**
         * findByEmailAndName => Email과 Name이 모두 일치하는 데이터만 찾는 AND연산
         * findByEmailOrName => Email과 Name중 둘 중 하나라도 일치하는 데이터를 찾는 OR연산
         */
        System.out.println("findByEmailAndName: "+userRepository.findByEmailAndName("martin@fastcampus.com","dennis"));
        System.out.println("findByEmailOrName: "+userRepository.findByEmailOrName("martin@fastcampus.com","dennis"));

        /**
         *  findByCreatedAtAfter => createdAt 값보다 작은값을 찾는 연산 => cratedAt > ?
         *  findByCratedAtBefore => createdAt 값도다 큰값을 찾는 연산 => createdAt < ?
         *  Atfer나 Before는 날짜계산에 주로사용
         */
        System.out.println("findByCreatedAtAfter: "+userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter: "+userRepository.findByIdAfter(4L));

        /**
         * findByCreatedAtGreaterThan => createdAt 값보다 작은값을 찾는 연산 => createdAt >?
         * findByCreatedAtGreaterThanEqual => cratedAt 값과 같거나 작은 값은값을 찾는 연산 => createdAt >= ?
         */
        System.out.println("findByCreatedAtGreaterThan: "+userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual: "+userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        /**
         * findByCreatedAtBetween은 두 값을 포함한 영역의 값을 찾는다 = > between 어제날짜 and 내일날짜 => 어제부터 내일까지의 날짜값을 가져온다.
         * 이거는 findByIdGreaterThanEqualAndIdLessThanEqual와 같다.
         */
        System.out.println("findByCreatedAtBetween: "+userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L),LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween: "+userRepository.findByIdBetween(1L,3L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual: "+userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L,3L));

        /**
         * findByIdIsNotNull는 Id가 Null이 아닌 것만 가져오는 키워드
         * findByAddressIsNotEmpty는 where exist(select절)을 사용함 name is not null and name != "" 이거 아님
         */
        System.out.println("findByIdIsNotNull: "+userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressIsNotEmpty: "+userRepository.findByAddressIsNotEmpty());

        /**
         * findByNameIn()은 ()안에 List문이 들어가야함. SQL문의 in구절이랑 같음
         */
        System.out.println("findByNameIn: "+userRepository.findByNameIn(Lists.newArrayList("martin","dennis")));

        /**
         *  findByNameStartingWith or EndingWith or Contains는 SQL문의 Like문이랑 같음
         *  StartingWith = Like(mar%)
         *  EndingWith = Like(%tin)
         *  contains = Like(%art%)
         *
         *  findByNameLike()는 파라미터안에 %%을 넣어야하는 불편함 있어서 대부분은 위의 메소드를 사용함
         */
        System.out.println("findByNameStartingWith: "+userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith: "+userRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains: "+userRepository.findByNameContains("art"));
        System.out.println("findByNameLike: "+userRepository.findByNameLike("%art%"));


    }

    @Test
    void pagingAndSortingTest(){
        /**
         * OrderBy는 SQL문의 OrderBy문과 같다
         * 정렬조건을 추가할때는 And문을 사용하지 않고 정렬하고자 하는 컬럼과 정렬방법을 적으면된다
         * ex)OrderbyIdDescEmailAsc id는 역순, 이메일을 정순으로 정렬
         * find 즉 select 할때 Sort변수를 넣어서 정렬도 가능한다.
         */
        System.out.println("findTop1ByName: "+userRepository.findTop1ByName("martin"));
        System.out.println("findTopByNameOrderByIdDesc: "+userRepository.findTopByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc: "+userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
        System.out.println("findFirstByNameWithSortParams: "+userRepository.findFirstByName("martin",Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        /**
         * pageRequet로 페이징관련 파라미터를 보내고 Page가 페이징관련 파라미터를 받는 역할은 한다.
         * pegaReqest의 of에 page,size,sort 파라미터를 입력한다.
         * page는 0가 시작, size는 한 페이지를 가져온 데이터의 크기, sort는 Order클래스를 쓴다.
         */
        System.out.println("findByNameWithPaging: "+userRepository.findByName("martin",PageRequest.of(1,1,Sort.by(Sort.Order.desc("id")))).getTotalElements());
    }

}
