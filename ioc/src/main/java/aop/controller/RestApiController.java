package aop.controller;

import aop.controller.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get")
    public void get(){
        System.out.println("get method");
        //System.out.println("get method"+id);
        //System.out.println("get method"+name);
    }

    @PostMapping("/post")
    public void post(@RequestBody User user ){
        System.out.println("post method : "+user);
    }

}
