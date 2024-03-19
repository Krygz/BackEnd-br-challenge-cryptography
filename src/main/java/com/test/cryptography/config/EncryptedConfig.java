package com.test.cryptography.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptedConfig {

    private final StringEncryptor stringEncryptor;

    public EncryptedConfig(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }
    public String encrypt(String s) {
         return stringEncryptor.encrypt(s);
    }
    public String decrypt(String s) {
        return stringEncryptor.decrypt(s);
    }
}
