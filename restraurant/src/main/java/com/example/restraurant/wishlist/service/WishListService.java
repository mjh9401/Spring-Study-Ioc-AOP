package com.example.restraurant.wishlist.service;

import com.example.restraurant.naver.NaverClient;
import com.example.restraurant.naver.dto.SearchImageReq;
import com.example.restraurant.naver.dto.SearchLocalReq;
import com.example.restraurant.naver.dto.SearchLocalRes;
import com.example.restraurant.wishlist.dto.WishLIstDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

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
}
