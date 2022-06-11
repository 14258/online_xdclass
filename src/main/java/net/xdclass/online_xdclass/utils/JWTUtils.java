package net.xdclass.online_xdclass.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.xdclass.online_xdclass.model.entity.User;

import java.util.Date;

/**
 * Jwt工具类
 * 1.生成的token，可以通过base64进行加密出明文信息
 * 2.base64解密出的明文信息，修改再编码，则会解密失败
 * 3.无法作废已颁布的token，除非改秘钥
 */
public class JWTUtils {

    /**
     * 过期时间 一周
     */
    private static final long EXPIRE = 60000 * 60 * 24 *7;

    /**
     * 加密秘钥
     */
    private static final String SECRET = "xdclass.net168";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "xdclass";

    /**
     * subject
     */
    private static final String SUBJECT = "xdclass";

    /**
     * 根据⽤户信息，⽣成令牌
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img",user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();

        token = TOKEN_PREFIX + token;

        return token;
    }

    /**
     * 校验token的⽅法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){
        try{
            final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }




}
