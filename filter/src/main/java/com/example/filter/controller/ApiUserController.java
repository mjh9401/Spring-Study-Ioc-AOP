package com.example.filter.controller;

import com.example.filter.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// lombok log 찍을 때 사용
@RestController
@RequestMapping("/api/temp")
public class ApiUserController {

    @PostMapping("")
    public User User(@RequestBody User user){
        log.info("temp : {}",user);
        return user;
    }
}
