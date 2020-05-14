package com.tonlp.springcloud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;


// @Component和@Value("${jwt.config.key}")注解不能到达其他模块
// 其他模块可以 使用该类、
@Data
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String key;

    private Long ttl;

    /**
     * 创建 JWT 字符串
     * @param id
     * @param name
     * @param roles
     * @return
     */
    public String createJWT(String id, String name, String roles){
        Date now = new Date();
        JwtBuilder jwt = Jwts.builder()
                .setId(id)
                .setSubject(name)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ttl))
                .signWith(SignatureAlgorithm.HS256, key);
        return jwt.compact();
    }

    public Claims parseJWT(String jwt){
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        }catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
