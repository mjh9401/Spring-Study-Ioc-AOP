package com.example.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //(ContentCachingRequestWrapper)request;
        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}",url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotatino : {}",hasAnnotation);

        // 나의 서버는 모두 public을 동작하는데
        // 단! Auth 권한을 가진 요청에 대해서는 세션 쿠키
        if(hasAnnotation){
            // 권한체크
            String query = uri.getQuery();
            log.info("query : {}",query);
            if(query.equals("name=mjh")){
                return true;
            }
            throw new AuthException();
        }

        return true;
    }

    public boolean checkAnnotation(Object handelr,Class clazz){
        // resource javascript,html은 통과
        if(handelr instanceof ResourceHttpRequestHandler){
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handelr;

        if(handlerMethod.getMethodAnnotation(clazz) != null || handlerMethod.getBeanType().getAnnotation(clazz) !=null){
            // Auth annotation이 있을 때 true
            return true;
        }

        return false;
    }
}
