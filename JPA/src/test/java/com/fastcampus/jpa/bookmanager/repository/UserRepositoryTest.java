package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author Martin
 * @since 2021/03/10
 */
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
//    @Transactional
    void crud() { // create, read, update, delete
//        //List<User> users = userRepository.findAllById(Lists.newArrayList(1L,2L,3L));
//        User user1 = new User("jack","jack@naver.com");
//        User user2 = new User("do","do@naver.com");
//        //userRepository.saveAll(Lists.newArrayList(user1,user2));
//        userRepository.save(user1);
//
//        List<User> users = userRepository.findAll();
//        users.forEach(System.out::println);

        User user = userRepository.findById(1L).orElse(null);
        System.out.println(user);

    }

}
