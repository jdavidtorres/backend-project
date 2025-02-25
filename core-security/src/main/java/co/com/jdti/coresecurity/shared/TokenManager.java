package co.com.jdti.coresecurity.shared;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class TokenManager {

	private static final String SECRET_KEY = "super-secure-secret-key-that-should-be-long-enough-256bits";
	private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	private static final long EXPIRATION_TIME_MS = 1000 * 60 * 60 * 2;

	public String generateToken(UserDetails user) {
		return Jwts.builder()
			.header()
			.type("JWT").and()
			.subject(user.getUsername())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
			.signWith(KEY, Jwts.SIG.HS256)
			.compact();
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload();
		} catch (Exception e) {
			log.error("Invalid token {}", e.getMessage());
			throw new RuntimeException("Invalid JWT token", e);
		}
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	public boolean isTokenValid(String token, UserDetails user) {
		return user.getUsername().equals(getUsername(token)) && !isTokenExpired(token);
	}
}
