package com.learnsecurity.banksecurity.filter;

//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.web.server.ServerWebExchange;
//
//import java.util.HashMap;
//import java.util.regex.MatchResult;
//
//public class AllExceptUrlsStartedWith implements ServerWebExchangeMatcher {
//
//    private static final String[] ALLOWED_METHOD = new String[] {"GET","HEAD","TRACE","OPTIONS"};
//    private final String[] allowedUrls;
//
//    public AllExceptUrlsStartedWith(String... allowedUrls) {
//        this.allowedUrls = allowedUrls;
//    }
//
//    @Override
//    public Mono<MatchResult> matches(ServerWebExchange exchange) {
//        MatchResult result = null;
//        String method = exchange.getRequest().getMethod().toString();
//        String uri = exchange.getRequest().getURI().toString();
//        for (String allowedMethod : ALLOWED_METHOD){
//            if (allowedMethod.equals(method)){
//               // result = new ServerWebExchangeMatcher.MatchResult(false,new HashMap<>(uri,method));
//            }
//        }
//
////
////        for (String allowedUrl : allowedUrls){
////            if (allowedUrl.equals(uri)){
////                result.m;
////            }
////        }
//        return Mono.just(result);
//    }
//}
