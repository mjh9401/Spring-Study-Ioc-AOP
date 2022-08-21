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
//    @Transactional : jpa getOne 메소드 사용시 세션유지를 위해 사용
    void crud() { // create, read, update, delete
//        //List<User> users = userRepository.findAllById(Lists.newArrayList(1L,2L,3L));
//        User user1 = new User("jack","jack@naver.com");
//        User user2 = new User("do","do@naver.com");
//        //userRepository.saveAll(Lists.newArrayList(user1,user2));
//        userRepository.save(user1);
//
//        List<User> users = userRepository.findAll();
//        users.forEach(System.out::println);

//        User user = userRepository.findById(1L).orElse(null);
//        System.out.println(user);
//        userRepository.saveAndFlush(new User("afaf","afaf@naver.com"));
//        userRepository.findAll().forEach(System.out::println); // flush는 db에 데이터를 넣는 시점에 대한 메소드
        
        // 데이터 갯수 확인
        long count = userRepository.count();
        System.out.println(count);
        
        // 해당 결과 존재여부 확인
        boolean exists = userRepository.existsById(1L);
        System.out.println(exists);
        
        // 데이터 삭제 delete는 select를 한번 하고 난뒤에 삭제
        userRepository.deleteById(1L);
        userRepository.deleteAll();
        // deleteInBatch는 select 없이 delete 실행
        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L,3L)));
        userRepository.deleteAllInBatch();

        // 페이징
        Page<User> users =userRepository.findAll(PageRequest.of(1,3));
        System.out.println("page: "+users);
        System.out.println("totalElements: "+users.getTotalElements());
        System.out.println("totalpages: "+users.getTotalPages());
        System.out.println("numberOfElements: "+users.getNumberOfElements());
        System.out.println("sort: "+users.getSort());
        System.out.println("size: "+users.getSize());

        // matcher로 검색색
        User user = new User();
        user.setEmail("slow");

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user, matcher);

        userRepository.findAll(example).forEach(System.out::println);

    }



}
