package com.groupeisi.commandeservice.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service; import java.security.Key; import java.util.*; import java.util.function.Function;
@Service
public class JwtService {
    @Value("${app.jwt.secret}") private String secretKey;
    @Value("${app.jwt.expiration}") private long expiration;
    public String generateToken(UserDetails u) {
        return Jwts.builder().setSubject(u.getUsername()).setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
    }
    public boolean isTokenValid(String t, UserDetails u) { return extractUsername(t).equals(u.getUsername()) && !isTokenExpired(t); }
    public String extractUsername(String t) { return extractClaim(t, Claims::getSubject); }
    private boolean isTokenExpired(String t) { return extractExpiration(t).before(new Date()); }
    private Date extractExpiration(String t) { return extractClaim(t, Claims::getExpiration); }
    public <T> T extractClaim(String t, Function<Claims,T> r) { return r.apply(Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(t).getBody()); }
    private Key getSignKey() { return Keys.hmacShaKeyFor(secretKey.getBytes()); }
}
