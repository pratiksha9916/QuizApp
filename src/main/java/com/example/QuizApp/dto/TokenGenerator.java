//package com.example.QuizApp.DTO;
//
//
//
//import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
//
//import java.util.Date;
//
//public class TokenGenerator {
//    public static void main(String[] args) {
//        String secretKey = "YourSuperSecretKeyForHS256";
//
//        String token = jwts.builder()
//                .setIssuer("https://dev-cu8lm630x8ip525.us.auth0.com/")
//                .setSubject("oWU3xVdyKCI6WjOFkFRs6EJHhvI7GTVS@clients")
//                .setAudience("https://quizapi")
//                .claim("scope", "readquiz updatequiz readquestion updatequestion deletequestion createquiz")
//                .claim("gty", "client-credentials")
//                .claim("azp", "oWU3xVdyKCI6WjOFkFRs6EJHhvI7GTVS")
//                .setIssuedAt(new Date(1751098313000L))
//                .setExpiration(new Date(1751184713000L))
//                .signWith(SignatureAlgorithm.RS256, secretKey.getBytes())
//                .compact();
//
//        System.out.println("RS256 Token: " + token);
//    }
//}
