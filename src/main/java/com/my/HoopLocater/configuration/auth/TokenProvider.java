package com.my.HoopLocater.configuration.auth;

import com.my.HoopLocater.common.exception.CustomAuthException;
import com.my.HoopLocater.domain.auth.dto.TokenDto;
import com.my.HoopLocater.domain.auth.dto.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    public static final String BEARER_TYPE = "Bearer";

    public static final String ACCESS_TOKEN_HEADER = "accessToken";
    public static final String BEARER_PREFIX = "Bearer+";

    public static final Long ACCESS_TOKEN_EXPIRE_TIME = (long) (1000 * 60 * 60 * 24 * 100); // 100일
    private final Key key;

    public TokenProvider(@Value("${jwt.secret-key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public TokenDto generateTokenDto(Authentication authentication) {
        var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        var now = (new Date()).getTime();

        var accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        // AccessToken 생성
        var accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenDto(BEARER_TYPE, accessToken, accessTokenExpiresIn.getTime());
    }

    public TokenDto generateTokenDtoFromUserDto(UserDto userDto) {
        var authorities = userDto.role();

        var now = (new Date()).getTime();

        // AccessToken 생성
        var accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        var accessToken = Jwts.builder()
                .setSubject(userDto.loginId())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new TokenDto(BEARER_TYPE, accessToken, accessTokenExpiresIn.getTime());
    }

    public Authentication getAuthentication(String accessToken) {
        var claims = parseClaims(accessToken);
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new CustomAuthException("권한정보가 없는 JWT 토큰입니다.");
        }

        var authorities = (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES_KEY);

        return new UsernamePasswordAuthenticationToken(new User(claims.getSubject(), "", authorities), "", authorities);
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (Exception e) {
            log.info("기타 JWT 토큰 검증 오류입니다.");
        }
        return false;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader("accessToken");
    }

}
