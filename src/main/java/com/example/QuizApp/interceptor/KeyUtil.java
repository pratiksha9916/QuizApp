package com.example.QuizApp.interceptor;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

public class KeyUtil {

    public static RSAPublicKey getPublicKeyFromPem(String Path) throws Exception {
        try (InputStream is = KeyUtil.class.getResourceAsStream("/auth0-public.pem")) {
            CertificateFactory fact = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) fact.generateCertificate(is);
            return (RSAPublicKey) cert.getPublicKey();
        }
    }
}

