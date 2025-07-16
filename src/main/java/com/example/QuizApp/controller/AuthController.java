package com.example.QuizApp.controller;

import com.example.QuizApp.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String decryptedPassword;

//        try {
//            decryptedPassword = AESUtil.decrypt(request.getEncryptedPassword());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Invalid password format.");
//        }

//        String tokenUrl = "https://dev-cu8lm6350x8ip525.us.auth0.com/oauth/token";
//
//        Map<String, Object> body = new HashMap<>();
//        body.put("grant_type", "client_credentials");
//        body.put("client_id", "qk4m2WwOEy4Spl8QqAvX1gu6GZQG2IdC");
//        body.put("client_secret", "LwGx2YzaBVMkx_Y9xtmWXzUpmt8ff5fsKxPmml1NFaxpPR0zO-mPjBVbpXX5nk0w");
//        body.put("audience", "https://quizapi"); // Important!
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        try {
//            ResponseEntity<String> tokenResponse =
//                    restTemplate.postForEntity(tokenUrl, entity, String.class);
//
//            System.out.println("Token acquired: " + tokenResponse.getBody());
//            return ResponseEntity.ok(tokenResponse.getBody());
//
//        } catch (HttpClientErrorException e) {
//            System.out.println("Auth0 Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
//            return ResponseEntity.status(e.getStatusCode()).body("Failed: " + e.getResponseBodyAsString());
//        }


        String tokenUrl = "https://dev-cu8lm6350x8ip525.us.auth0.com/oauth/token";

        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "password");
        body.put("username", request.getUsername());
        body.put("password", request.getEncryptedPassword()); // plain-text (not encrypted)
        body.put("client_id", "anUspZPlajbvU6BsdDw6DdMWoOcZ55B0");
        body.put("client_secret", "5ujCXB_8CaLRG_pih_3CL0kXxF-rNQ2O-Zmzz-vCOUiJGY-QbdUxOxqPS6z5m1q9");
        body.put("audience", "https://quizapi"); // Optional
        body.put("connection", "Username-Password-Authentication"); // ✅ Add this!
        body.put("scope", " readquestion");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenUrl, entity, String.class);

            // ✅ Login successful
            return ResponseEntity.ok(tokenResponse.getBody());

        } catch (HttpClientErrorException e) {
            // ❌ Invalid credentials or other error
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid username or password.");
            }
            return ResponseEntity.status(e.getStatusCode())
                    .body("Login failed: " + e.getResponseBodyAsString());
        }
    }
    }

