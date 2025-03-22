package com.xll.frame.starter.common.config.mybatis;

import com.xll.frame.starter.security.crypto.encryptor.IEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 功能描述: <br>
 * <p>
 *  <BCrypt 加/解密处理器（不可逆）>
 * </p>
 * @author xuliangliang
 * @since 2025/2/23 16:21
 * @version 1.0.0
 */
public class BCryptEncryptor implements IEncryptor {

    private final PasswordEncoder passwordEncoder;

    public BCryptEncryptor(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encrypt(String plaintext, String password, String publicKey) throws Exception {
        return passwordEncoder.encode(plaintext);
    }

    @Override
    public String decrypt(String ciphertext, String password, String privateKey) throws Exception {
        return ciphertext;
    }
}
