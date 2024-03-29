package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    Set<User> findByName(String name);
    Set<User> findUserByNameIs(String name);
    Set<User> findUserByName(String name);
    Set<User> findUserByNameEquals(String name);
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);
    User findSomethingByEmail(String email);
    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);
    List<User> findLast1ByName(String name);
    List<User> findByEmailAndName(String email,String name);
    List<User> findByEmailOrName(String email,String name);
    List<User> findByCreatedAtAfter(LocalDateTime yesterday);
    List<User> findByIdAfter(Long id);
    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);
    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
    List<User> findByIdBetween(Long id1, Long id2);
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);
    List<User> findByIdIsNotNull();
//    List<User> findByAddressIsNotEmpty(); // where exist(select절)을 사용함 name is not null and name != "" 이거 아님
    List<User> findByNameIn(List<String>names);
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
    List<User> findByNameLike(String name);
    List<User> findTop1ByName(String name);
    List<User> findTopByNameOrderByIdDesc(String name);
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);
    List<User> findFirstByName(String name, Sort sort);
    
    // 페이징을 위한 메소드
    Page<User> findByName(String name, Pageable pageable);
    /*@Query는 JPA의 도움받아 쿼리를 생성하지 않고 직접 쿼리를 작성할 때 사용한다. nativeQuery는 true로 해야한다.*/
    @Query(value = "select * from user limit 1;",nativeQuery = true)
    Map<String,Object> findRowRecord();
}
