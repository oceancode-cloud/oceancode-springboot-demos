package com.springboot.simple.demo.core.service;

import com.oceancode.cloud.api.security.Rsa2CryptoService;
import com.springboot.simple.demo.web.AppMain;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RsaTest {

    @Resource
    private Rsa2CryptoService rsa2CryptoService;

    private static String password = "root";

    @Test
    public void test() {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlwRUQO9wCs7l/kwhJuwO9GV90e0NED3ZOAIbGkEMclevEA9fx1/+gJKFw2leMuejjlTdMnP7YqCEkIzZHoKpGF9TH7v4SEb0POKwXfD/zZ9aEcGKJtEebo3U6S7gNSKdSAZaiFM2tRV9SCJZ1Tp6J1Ful02oR7imWny1Jm0Cpm+4ekpffm7Pe3PsgttxqvJC83PiV6oE5hcpZVPlt4jtmrWcoCjVrmuH6EzY5JfW/OQiM83WLuY9xX7NN4rytMX82vWGwf4dKhegeqy1QJpsWJq7wvgV2NQY+CwTLpNlnbni+WNS3/aFIe+6NcePS80fvho/17I4PwUZqvLJhef7YQIDAQAB";
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXBFRA73AKzuX+TCEm7A70ZX3R7Q0QPdk4AhsaQQxyV68QD1/HX/6AkoXDaV4y56OOVN0yc/tioISQjNkegqkYX1Mfu/hIRvQ84rBd8P/Nn1oRwYom0R5ujdTpLuA1Ip1IBlqIUza1FX1IIlnVOnonUW6XTahHuKZafLUmbQKmb7h6Sl9+bs97c+yC23Gq8kLzc+JXqgTmFyllU+W3iO2atZygKNWua4foTNjkl9b85CIzzdYu5j3Ffs03ivK0xfza9YbB/h0qF6B6rLVAmmxYmrvC+BXY1Bj4LBMuk2WdueL5Y1Lf9oUh77o1x49LzR++Gj/Xsjg/BRmq8smF5/thAgMBAAECggEBAIb2fX3S/uUJ5w4PZ4lraioNEhcsrYIYZCrj5ujdvYvqxS+rLcP3DHJHgHgVkPQZhFhNi6ntcXrBTfgJiHj1+L0pZBZ2XPvrPZe0WD58hYB0q4A24YqzVWkkJGWJWSLjnoCh/QLcq4XGgMKYwIQJ0OWWpyldY8lFQpaX4SVIQxgOWPrJwbjFMuGEh7A6EZF+3Et2KpUOJ5QRIqT1NrMCK18RDzXHoHfsXmx4xuAq+HWVz0/q8sxO9wT4i0QqBUDs9oyupvaV2OLsXmwy2GENu5mbKeqPPAAD/dlgblVTyc0496vVNJEdY6LCQJjYhd4sS6oKrUz/1SgzVd0NDZAcQykCgYEA5H8qz1DzZH78ViZ+vS3DZde0roeNob1mcPRhTRhS23u8FQW5QQ5FBII+Xxy3CzzD6spf0T29bl0MPCN8hT/lIx6lne7FdzFfhOwXx8WuTzKHj2bdXeQuinu/kWrLl8kstUt2tw4nI9ycoCusfTBgt5f4en/XMMzEDnTaTYS4CRcCgYEAqTG4eGpdJaKmI+cX9h1a3InsykB5Ij4Gj4Vgp8dtiPnmPo3clpOy2cNDiCOj4ZN2pDh+IsWZxef0nZfkiAA6ezInR5sNbomHPpmQgrWza9XgG0MkUIKKqLa9BN1gJibZ20SiuyO8vUs1UBoZHkOFVH8GR/rvcu8OjTUEM3YL+kcCgYEAkwp6xBr15hAJ1m7DVH5kpd8f+MhmjlTR3ZKGIxSqDXAc4vJh+slyBKrD9HUIHgUmlqtQ6dfvVVwviWvUr/MkQ2tvbKjBDikJrQI5c2d0+qYrsk6h2dADWz6zUqMFDVJ6oNhFNxu2eE95w2EXiSBevQbg8gPeuRqseBkeepAT1lUCgYAOcmxp+8sQV58QGb92CHOluTE64950Cg/NWXozWVgFBjGezcY0JVB0P7QYf9KShGDxDNHYbj7/IxtECaA97KcRteqctqSi96NQuRQZ1SII5y5HpagIJ91nIpNpQu3e1BpuGqI6kIahMzbOLCxQ5TsRR/dlVgv0KG15qTk+m6nSKQKBgBYoaOduzhLS9kJZPQmWmaBAdc5enZQ1Ype2mqhNwYTZgK5cMkzvQgnF0UCAHZqFC5kGwBtfAfcHHYo9iQ90KDuZhpAlN/P/ysAoeiP5w0QehvaKsRT+TH/1hPWXFjbgkvZBgBZBaZV+mNQtlJh2W2TxTtK1AX+fO5msc8hDunJd";

        String rawStr = rsa2CryptoService.encryptByPublicKey(password, publicKey);
        Assert.assertEquals(password, rsa2CryptoService.decryptByPrivateKey(rawStr, privateKey));

        String rawStr2 = rsa2CryptoService.encryptByPrivateKey(password, privateKey);
        Assert.assertEquals(password, rsa2CryptoService.decryptByPublicKey(rawStr2, publicKey));
    }

    public void smTest() {
        String publicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEGKKBITA1l5WYcEMbDBHC/Xh9fGgr9QyFrqAmjMM66zTXtr8ORmI3zT/zsgNPSk7IZEfzjDWlXWAfUBUENQpbGA==";
        String privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQg6DihwOOwlldx2v19wRU26+FmVvf/UqlcLMx3Kl2Zp0qgCgYIKoEcz1UBgi2hRANCAAQYooEhMDWXlZhwQxsMEcL9eH18aCv1DIWuoCaMwzrrNNe2vw5GYjfNP/OyA09KTshkR/OMNaVdYB9QFQQ1ClsY";
    }
}
