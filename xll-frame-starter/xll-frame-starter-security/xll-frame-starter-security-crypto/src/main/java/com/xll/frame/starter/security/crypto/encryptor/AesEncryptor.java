package com.xll.frame.starter.security.crypto.encryptor;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * 功能描述: <br>
 * <p>
 *  <AES（Advanced Encryption Standard） 加/解密处理器>
 * </p>
 * @author xuliangliang
 * @since 2025/2/6 22:03
 * @version 1.0.0
 */
public class AesEncryptor extends AbstractSymmetricCryptoEncryptor {

    @Override
    protected SymmetricAlgorithm getAlgorithm() {
        return SymmetricAlgorithm.AES;
    }
}
