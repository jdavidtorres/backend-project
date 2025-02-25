package co.com.jdti.coresecurity.filter;

import co.com.jdti.coresecurity.services.IAuthService;
import co.com.jdti.coresecurity.shared.TokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final TokenManager tokenManager;
	private final IAuthService iAuthService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		final String authenticationHeader = request.getHeader("Authorization");

		if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authenticationHeader.substring(7);
		String username = tokenManager.getUsername(token);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			var userDetails = iAuthService.loadUserByUsername(username);

			if (tokenManager.isTokenValid(token, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.debug("Authenticated user {}", username);
			} else {
				log.error("Invalid token");
			}
		}

		filterChain.doFilter(request, response);
	}
}
