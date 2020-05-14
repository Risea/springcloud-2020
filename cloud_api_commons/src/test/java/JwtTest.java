import cn.hutool.core.codec.Base64;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

// JWT介绍 https://www.cnblogs.com/woshiweige/p/11068626.html
public class JwtTest {

    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("123")
                .setSubject("Seands")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+1000*60L))
                .claim("role", "admin")
                .signWith(SignatureAlgorithm.HS256, "Seands");
                //.signWith(SignatureAlgorithm.RS256, "Seands");        // RSA256可支持公钥和私钥、将生成token和验证token分开
        System.out.println(jwtBuilder.compact());
        // eyJhbGciOiJIUzI1NiJ9
        // .eyJqdGkiOiIxMjMiLCJzdWIiOiJTZWFuZHMiLCJpYXQiOjE1ODgwNDkxMzksImV4cCI6MTU4ODA0OTE5OSwicm9sZSI6ImFkbWluIn0
        // .fNSE2F86tjqlub-i2ZSTnXauZsp_7VvlL8hF1UmrQL8

        // 第一部分 头部信息 Base64编码
        String header = new String(Base64.decode("eyJhbGciOiJIUzI1NiJ9"));
        System.out.println("Header: "+header);
        // 第二部分 body 也是 Base64编码
        String body = new String(Base64.decode("eyJqdGkiOiIxMjMiLCJzdWIiOiJTZWFuZHMiLCJpYXQiOjE1NzY5MzQ4MTQsImV4cCI6MTU3NjkzNDg3NCwicm9sZSI6ImFkbWluIn0"));
        System.out.println(body);   // {"jti":"123","sub":"Seands","iat":1576922142}
        // 第三部分 是将 Base64(第一部分) + "." + Base64(第二部分) 后,采用第一部分alg的算法
        // 和 密钥 生成
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJTZWFuZHMiLCJpYXQiOjE1ODgwNDc1MTYsImV4cCI6MTU4ODA0NzU3Niwicm9sZSI6ImFkbWluIn0";

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec("Seands".getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(s.getBytes());
            String hash = byteArrayToHexString(bytes);
            System.out.println(hash);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }

    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }
}
