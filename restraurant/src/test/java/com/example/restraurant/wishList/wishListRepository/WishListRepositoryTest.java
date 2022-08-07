package com.example.restraurant.wishList.wishListRepository;

import com.example.restraurant.wishlist.entity.WishLIstEntity;
import com.example.restraurant.wishlist.repository.WishLIstRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    private WishLIstRepository wishLIstRepository;

    private WishLIstEntity create(){
        var wishList = new WishLIstEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAdress("roadAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisit(null);

        return wishList;
    }

    @Test
    public void saveTest(){
        var wishLIstEntity = create();
        var expected = wishLIstRepository.save(wishLIstEntity);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1,expected.getIndex());
    }

    @Test
    public void updateTest(){
        var wishLIstEntity = create();
        var expected = wishLIstRepository.save(wishLIstEntity);

        expected.setTitle("update test");
        var saveEntity = wishLIstRepository.save(expected);

        Assertions.assertEquals("update test",saveEntity.getTitle());
        Assertions.assertEquals(1,wishLIstRepository.listAll().size());
    }
    @Test
    public void findByIdTest(){
        var wishLIstEntity = create();
        wishLIstRepository.save(wishLIstEntity);

        var expected = wishLIstRepository.findById(1);

        Assertions.assertEquals(true,expected.isPresent());
        Assertions.assertEquals(1,expected.get().getIndex());
    }

    @Test
    public void deleteTest(){
        var wishLIstEntity = create();
        wishLIstRepository.save(wishLIstEntity);

        wishLIstRepository.deleteById(1);

        int count = wishLIstRepository.listAll().size();

        Assertions.assertEquals(0,count);
    }

    @Test
    public void listAllTest(){
        var wishLIstEntity1 = create();
        wishLIstRepository.save(wishLIstEntity1);

        var wishLIstEntity2 = create();
        wishLIstRepository.save(wishLIstEntity2);

        int count = wishLIstRepository.listAll().size();
        Assertions.assertEquals(2,count);
    }
}
