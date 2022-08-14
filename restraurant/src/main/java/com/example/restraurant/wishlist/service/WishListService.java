package com.example.restraurant.wishlist.service;

import com.example.restraurant.naver.NaverClient;
import com.example.restraurant.naver.dto.SearchImageReq;
import com.example.restraurant.naver.dto.SearchLocalReq;
import com.example.restraurant.wishlist.dto.WishLIstDto;
import com.example.restraurant.wishlist.entity.WishLIstEntity;
import com.example.restraurant.wishlist.repository.WishLIstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishLIstRepository wishLIstRepository;

    public WishLIstDto search(String query){
        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                var result = new WishLIstDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAdress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }

        return new WishLIstDto();
    }

    public WishLIstDto add(WishLIstDto wishLIstDto) {
        var entity = dtoToEntity(wishLIstDto);
        var saveEntity = wishLIstRepository.save(entity);

        return entityToDto(saveEntity);
    }

    private WishLIstEntity dtoToEntity(WishLIstDto wishLIstDto){
        var entity = new WishLIstEntity();
        entity.setIndex(wishLIstDto.getIndex());
        entity.setTitle(wishLIstDto.getTitle());
        entity.setCategory(wishLIstDto.getCategory());
        entity.setAddress(wishLIstDto.getAddress());
        entity.setRoadAdress(wishLIstDto.getRoadAdress());
        entity.setHomePageLink(wishLIstDto.getHomePageLink());
        entity.setImageLink(wishLIstDto.getImageLink());
        entity.setVisit(wishLIstDto.isVisit());
        entity.setVisitCount(wishLIstDto.getVisitCount());
        entity.setLastVisit(wishLIstDto.getLastVisit());

        return entity;
    }

    private WishLIstDto entityToDto(WishLIstEntity wishLIstEntity){
        var dto = new WishLIstDto();
        dto.setIndex(wishLIstEntity.getIndex());
        dto.setTitle(wishLIstEntity.getTitle());
        dto.setCategory(wishLIstEntity.getCategory());
        dto.setAddress(wishLIstEntity.getAddress());
        dto.setRoadAdress(wishLIstEntity.getRoadAdress());
        dto.setHomePageLink(wishLIstEntity.getHomePageLink());
        dto.setImageLink(wishLIstEntity.getImageLink());
        dto.setVisit(wishLIstEntity.isVisit());
        dto.setVisitCount(wishLIstEntity.getVisitCount());
        dto.setLastVisit(wishLIstEntity.getLastVisit());

        return dto;
    }

    public List<WishLIstDto> findAll() {

        return wishLIstRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        wishLIstRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishLIstRepository.findById(index);
        if(wishItem.isPresent()){
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
