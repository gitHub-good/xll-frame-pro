package com.xll.frame.starter.security.crypto.enums;


import com.xll.frame.starter.security.crypto.encryptor.*;

/**
 * 功能描述: <br>
 * <p>
 *  <加密/解密算法枚举>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 21:59
 * @version 1.0.0
 */
public enum Algorithm {

    /**
     * AES
     */
    AES(AesEncryptor.class),

    /**
     * DES
     */
    DES(DesEncryptor.class),

    /**
     * PBE With MD5 And DES
     */
    PBE_WITH_MD5_AND_DES(PbeWithMd5AndDesEncryptor.class),

    /**
     * RSA
     */
    RSA(RsaEncryptor.class),

    /**
     * Base64
     */
    BASE64(Base64Encryptor.class),;

    /**
     * 加密/解密处理器
     */
    private final Class<? extends IEncryptor> encryptor;

    Algorithm(Class<? extends IEncryptor> encryptor) {
        this.encryptor = encryptor;
    }

    public Class<? extends IEncryptor> getEncryptor() {
        return encryptor;
    }
}
