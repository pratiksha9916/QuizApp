package com.example.QuizApp.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.example.QuizApp.interceptor.KeyUtil.getPublicKeyFromPem;

//@Component
//public class JwtAuthInterceptor implements HandlerInterceptor {
//    //move below configurations into properties file
//    private final String issuer = "https://dev-cu8lm6350x8ip525.us.auth0.com/";
//    private final String audience = "https://quizapi";
//    private final String requiredScope = "readquiz updatequiz readquestion updatequestion deletequestion createquiz";
//
//    private final Algorithm algorithm;
//
//    public JwtAuthInterceptor() throws Exception {
//        RSAPublicKey publicKey = getPublicKeyFromPem(); // load your PEM public key
//        this.algorithm = Algorithm.RSA256(publicKey, null);
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        System.out.println("Interceptor triggered");
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null  || !authHeader.startsWith("Bearer ")) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("text/plain");
//            response.getWriter().write("Missing or invalid Authorization header");
//            response.getWriter().flush();
//            return false;
//        }
//
//        String token = authHeader.substring(7);
//
//        if (!validateToken(token)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("text/plain");
//            response.getWriter().write("Invalid or unauthorized token");
//            response.getWriter().flush();
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean validateToken(String token) {
//        //verify the token using key
//        try {
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer(issuer)
//                    .withAudience(audience)
//                    .build();
//
//            DecodedJWT jwt = verifier.verify(token);
//            String scopeClaim = jwt.getClaim("scope").asString();
//            if (scopeClaim == null) {
//                System.err.println("Scope claim is missing");
//                return false;
//            }
//
////            String scopeClaim = jwt.getClaim("scope").asString(); // e.g., "readquiz updatequiz"
//            Set<String> tokenScopes = new HashSet<>(Arrays.asList(scopeClaim.split(" ")));
//            Set<String> requiredScopes = new HashSet<>(Arrays.asList(requiredScope.split(" ")));
//
//            if (!tokenScopes.containsAll(requiredScopes)) {
//                System.err.println("JwtAuthInterceptor: Missing required scopes");
//                return false;
//            }
//
//            return true;
//        } catch (Exception e) {
//            System.err.println("JwtAuthInterceptor: Token verification failed");
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
//
@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final Auth0Properties auth0Properties;
    private final Algorithm algorithm;

    @Autowired
    public JwtAuthInterceptor(Auth0Properties auth0Properties) throws Exception {
        this.auth0Properties = auth0Properties;
        RSAPublicKey publicKey = getPublicKeyFromPem(auth0Properties.getPublicKeyPath());
        this.algorithm = Algorithm.RSA256(publicKey, null);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor triggered");

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain");
            response.getWriter().write("Missing or invalid Authorization header");
            response.getWriter().flush();
            return false;
        }

        String token = authHeader.substring(7);

        if (!validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain");
            response.getWriter().write("Invalid or unauthorized token");
            response.getWriter().flush();
            return false;
        }

        return true;
    }

    private boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(auth0Properties.getIssuer())
                    .withAudience(auth0Properties.getAudience())
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            String scopeClaim = jwt.getClaim("scope").asString();

            if (scopeClaim == null) {
                System.err.println("Scope claim is missing");
                return false;
            }

            Set<String> tokenScopes = new HashSet<>(Arrays.asList(scopeClaim.split(" ")));
            Set<String> requiredScopes = new HashSet<>(Arrays.asList(auth0Properties.getRequiredScope().split(" ")));

            if (!tokenScopes.containsAll(requiredScopes)) {
                System.err.println("JwtAuthInterceptor: Missing required scopes");
                return false;
            }

            return true;
        } catch (Exception e) {
            System.err.println("JwtAuthInterceptor: Token verification failed");
            e.printStackTrace();
            return false;
        }
    }
}
