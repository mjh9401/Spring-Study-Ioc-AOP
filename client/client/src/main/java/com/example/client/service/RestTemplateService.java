package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    // response

    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9091")
                .path("/api/server/hello")
                .queryParam("name","asdsd")
                .queryParam("age",99)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri,UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse post(){
        // http://localhost:9091/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9091")
                .path("api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"mjh")
                .toUri();

        System.out.println(uri);

        // http body -> object - > objectmapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("mjh");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
     }

     public UserResponse exchange(){
         URI uri = UriComponentsBuilder
                 .fromUriString("http://localhost:9091")
                 .path("api/server/user/{userId}/name/{userName}")
                 .encode()
                 .build()
                 .expand(100,"mjh")
                 .toUri();

         System.out.println(uri);

         // http body -> object - > objectmapper -> json -> rest template -> http body json
         UserRequest req = new UserRequest();
         req.setName("mjh");
         req.setAge(10);

         RequestEntity<UserRequest> requestRequestEntity = RequestEntity
                 .post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .header("x-authorization","abcd")
                 .header("custum-header","ffff")
                 .body(req);


         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<UserResponse> response = restTemplate.exchange(requestRequestEntity,UserResponse.class);

         return response.getBody();
     }

     public Req<UserResponse> genericExchange(){
         URI uri = UriComponentsBuilder
                 .fromUriString("http://localhost:9091")
                 .path("api/server/user/{userId}/name/{userName}")
                 .encode()
                 .build()
                 .expand(100,"mjh")
                 .toUri();

         System.out.println(uri);

         // http body -> object - > objectmapper -> json -> rest template -> http body json
         UserRequest userRequest = new UserRequest();
         userRequest.setName("mjh");
         userRequest.setAge(10);

         Req<UserRequest> req = new Req<UserRequest>();
         req.setHeader(new Req.Header());
         req.setResBody(userRequest);

         RequestEntity<Req<UserRequest>> requesttEntity = RequestEntity
                 .post(uri)
                 .contentType(MediaType.APPLICATION_JSON)
                 .header("x-authorization","abcd")
                 .header("custum-header","ffff")
                 .body(req);

         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<Req<UserResponse>> response = restTemplate.exchange(requesttEntity,new ParameterizedTypeReference<>(){});

         return response.getBody();
     }
}
