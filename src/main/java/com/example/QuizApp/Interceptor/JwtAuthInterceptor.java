package com.example.QuizApp.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nimbusds.jose.JWSAlgorithm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.example.QuizApp.Interceptor.KeyUtil.getPublicKeyFromPem;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final String issuer = "https://dev-cu8lm6350x8ip525.us.auth0.com/oauth/token";
    private final String audience = "https://quizapi";
    private final String requiredScope = "readquiz updatequiz ,readquestion updatequestion deletequestion createquiz";
    private final Algorithm algorithm = Algorithm.RSA256(getPublicKeyFromPem());

    public JwtAuthInterceptor() throws Exception {
    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        System.out.println("Interceptor");
//
//        String header = request.getHeader("Authorization");
//
//        System.out.println(header);
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            System.out.println("Authorization header missing or invalid");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//
//
//
//        String token = header.substring(7).trim(); // Remove "Bearer "
//
//        try {
//            RSAPublicKey publicKey = getPublicKeyFromPem();
//
//            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer(issuer)
//                    .withAudience(audience)
//
//                    .build();
//
//            DecodedJWT jwt = verifier.verify(token);
//            String tokenScope = jwt.getClaim("scope").asString();
//            if (tokenScope == null) {
//
//                System.out.println("First");
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                return false;
//            }
//
//            if (!hasRequiredScopes(tokenScope, requiredScope)) {
//
//                System.out.println("Second");
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                return false;
//            }
//
//            // Optionally store info in request attributes
//            request.setAttribute("user", jwt.getSubject());
//            return true;
//
//        } catch (Exception e) {
//
//
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//    }
//
//    private boolean hasRequiredScopes(String tokenScope, String requiredScopes) {
//        // Split scopes by space, convert to sets for easy checking
//        Set<String> tokenScopesSet = new HashSet<>(Arrays.asList(tokenScope.split(" ")));
//        Set<String> requiredScopesSet = new HashSet<>(Arrays.asList(requiredScopes.split(" ")));
//
//        return tokenScopesSet.containsAll(requiredScopesSet);
//    }
//}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Interceptor");

        // Get the Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false; // reject the request

        }

        // Extract token from header
        String token = authHeader.substring(7); // remove "Bearer " prefix

        System.out.println(token);
        // Validate the token (you can implement your own logic here)
        boolean valid = validateToken(token);
        if (!valid) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false; // reject the request
        }

        System.out.println("Interceptor22");
        // Token valid, proceed with request
        return true;
    }

    // Example token validation method (you can replace this with real validation)
    private boolean validateToken(String token) {
        // For demonstration: check token equals some fixed string or call your token validation logic
        return "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjV3ZEpCSkZNNVVXSU1QaXBkWHdPdSJ9.eyJpc3MiOiJodHRwczovL2Rldi1jdThsbTYzNTB4OGlwNTI1LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJxazRtMld3T0V5NFNwbDhRcUF2WDFndTZHWlFHMklkQ0BjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly9xdWl6YXBpIiwiaWF0IjoxNzUxMTk1MDM0LCJleHAiOjE3NTEyODE0MzQsInNjb3BlIjoicmVhZHF1aXogdXBkYXRlcXVpeiByZWFkcXVlc3Rpb24gdXBkYXRlcXVlc3Rpb24gZGVsZXRlcXVlc3Rpb24gY3JlYXRlcXVpeiIsImd0eSI6ImNsaWVudC1jcmVkZW50aWFscyIsImF6cCI6InFrNG0yV3dPRXk0U3BsOFFxQXZYMWd1NkdaUUcySWRDIn0.blXDQ8XXMTUgUlbcYqHIo3qtgBTaDtx1YG75XH0cHJvt1nfjVIT8svH5KGFHQLdREKpgMDYcWWJsgiIS_h9yTCo5_GqBWiVg_oCp2itCKxbFwDl2dBG3Xd1TwldJP7BnG-YrD7fVb5Rk0JokQASl8nrjo7HoKc0y5Uvcsut9aKFgQCj5Ln-Vpj03BpDc4cxxgdekI2x3cJB-ohAlJxFn0sumlTOkeZRFvEI7tDqr9LYUDkUcv9DxuJdsLzaA2xlaE4Xsr0BSycWa8LXG99RJ-4yDKClTdh4lFiFdYp0mH6j5cMq5on6LhquPd_X0QipVpNtOVEVktrWnI_sOXvdWiA".equals(token);
    }
}




