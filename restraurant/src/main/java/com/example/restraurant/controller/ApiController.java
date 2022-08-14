package com.example.restraurant.controller;

import com.example.restraurant.wishlist.dto.WishLIstDto;
import com.example.restraurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class ApiController {

    private final WishListService wishListService;

    @GetMapping("/search")
    public WishLIstDto search(@RequestParam String query){
        return wishListService.search(query);
    }

    @PostMapping("")
    public WishLIstDto add(@RequestBody WishLIstDto wishLIstDto){
        log.info("{}",wishLIstDto);

        return wishListService.add(wishLIstDto);
    }

    @GetMapping("/all")
    public List<WishLIstDto> findAll(){
        return wishListService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        wishListService.delete(index);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);
    }
}
