package com.example.QuizApp.Interceptor;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtil {

    public static RSAPublicKey getPublicKeyFromPem() throws Exception {
        try (InputStream is = KeyUtil.class.getResourceAsStream("/auth0-public.pem")) {
            CertificateFactory fact = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) fact.generateCertificate(is);
            return (RSAPublicKey) cert.getPublicKey();
        }
    }
}

