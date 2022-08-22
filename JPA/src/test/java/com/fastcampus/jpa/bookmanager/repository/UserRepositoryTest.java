package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

/**
 * @author Martin
 * @since 2021/03/10
 */
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
         */
        System.out.println("findTop2ByName: "+userRepository.findTop2ByName("martin"));
        System.out.println("findFirst2ByName: "+userRepository.findFirst2ByName("martin"));
        System.out.println("findLast1ByName: "+userRepository.findLast1ByName("martin"));

    }



}
