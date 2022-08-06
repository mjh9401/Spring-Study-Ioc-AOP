package com.example.restraurant.naver;

import com.example.restraurant.naver.dto.SearchImageReq;
import com.example.restraurant.naver.dto.SearchImageRes;
import com.example.restraurant.naver.dto.SearchLocalReq;
import com.example.restraurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;


    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();
        var header = new HttpHeaders();
        header.set("X-Naver-Client-Id",naverClientId);
        header.set("X-Naver-Client-Secret",naverClientSecret);
        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(header);
        var responeseType = new ParameterizedTypeReference<SearchLocalRes>(){};

        var responseEntity= new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responeseType
        );

        return responseEntity.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq){
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();
        var header = new HttpHeaders();
        header.set("X-Naver-Client-Id",naverClientId);
        header.set("X-Naver-Client-Secret",naverClientSecret);
        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(header);
        var responeseType = new ParameterizedTypeReference<SearchImageRes>(){};

        var responseEntity= new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responeseType
        );

        return responseEntity.getBody();
    }

}
