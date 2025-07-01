package com.example.QuizApp.Interceptor;


import com.auth0.jwt.algorithms.Algorithm;

import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;


public class JwtVerifierUtil {
    public static Algorithm getAlgorithmFromPublicKey() throws Exception {
        String pem = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwgsJWGBCuOSjmgYskthB\n" +
                "wL54x4hHmrfuWIbih2h1Oqun6LNel/1krKxWbEAEh6Um/q8Y+Y0zKd/vnP9m42lb\n" +
                "US/kAzxDRKZGZdfXNoLPNrblyzvrp/AL6gnFjeH5cI1ib1jnRd++DT+vabfioBDV\n" +
                "mbycMeb6Zfy/t9j9x+LhwtsrPXuWFcnyb4RePN/8zk4sINV0Y60KTVoQ6VRBTTf7\n" +
                "LEDO2qp5gxUnCj3YvU0+Agoo5AZEIPi3GWYydyZRFkEOy6bDHagV1hA88ps9W91U\n" +
                "tfoSzFJbDib7rYxFvhleCYH8vF5EfOJV B5fQfIFhSNyTShdh2ihJb22IjW+xznpM\n" +
                "HQIDAQAB\n" +
                "-----END PUBLIC KEY-----";

        String publicKeyPEM = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(keySpec);

        return Algorithm.RSA256(publicKey, null);
    }
}
