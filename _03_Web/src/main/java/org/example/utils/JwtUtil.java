package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    // 有效期为一小时
    private static final long JWT_TTL = 60 * 60 * 1000L;
    // 设置秘钥明文
    private static final String JWT_KEY = "sangeng";


    // 创建token
    public static String createJWT(String id, String subject, Long ttlMillis){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        if(ttlMillis == null){
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        SecretKey secretKey = generateKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)  // 唯一的id
                .setSubject(subject)    // 主题，可以是json数据
                .setIssuer("sg")    // 签发者
                .setIssuedAt(now)   // 签发时间
                .signWith(signatureAlgorithm, secretKey)    // 使用HS256对称加密算法
                .setExpiration(exp);    // 过期时间

        return  builder.compact();
    }

    // 生成加密后的秘钥
    public static SecretKey generateKey(){
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    // 解析秘钥
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generateKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    // 测试
    public static void main(String[] args) throws Exception {
        // 生成token
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), "sangeng", null);
        System.out.println(token);

        // 解析token
        // 超时或错误会抛出异常
        Claims claims = JwtUtil.parseJWT(token);
        String subject = claims.getSubject();
        System.out.println(subject);
    }
}
