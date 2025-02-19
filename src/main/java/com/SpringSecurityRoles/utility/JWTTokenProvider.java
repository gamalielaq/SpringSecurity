package com.SpringSecurityRoles.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import static java.util.Arrays.stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.SpringSecurityRoles.constants.SecurityConstant;
import com.SpringSecurityRoles.domain.UserPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JWTTokenProvider {
	 // Referecnce:-> https://github.com/auth0/java-jwt

	@Value("${jwt.secret}")
	private String secret;

	public String generateJwtToken(UserPrincipal userPrincipal) {
		// Este metodo es el que genera el token, despues de que el usuario se haya
		// autenticado
		String[] claims = getClaimsFromUser(userPrincipal);
		return JWT.create().withIssuer(SecurityConstant.GET_ARRAYS_LLC)
				.withAudience(SecurityConstant.GET_ARRAYS_ADMINISTRATION).withIssuedAt(new Date())
				.withSubject(userPrincipal.getUsername()).withArrayClaim(SecurityConstant.AUTHORITIES, claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(secret.getBytes()));
	}

	public List<GrantedAuthority> getAuthorities(String token) {
		String[] claims = getClaimsFromToken(token);
		return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	/*
	 * getAuthentication indica si el usuario esta autenticado,
	 * 
	 * @return
	 */
	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities,
			HttpServletRequest request) {
		UsernamePasswordAuthenticationToken userPasswordAuthToken = new UsernamePasswordAuthenticationToken(username,
				null, authorities);
		userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return userPasswordAuthToken;
	}

	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier = this.getJWTVerifier();
		return StringUtils.isNoneEmpty(username) && !isTokenExpired(verifier, token);
	}

	public String getSubjet(String token) {
		JWTVerifier verifier = this.getJWTVerifier();
		return verifier.verify(token).getSubject();
	}

	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expiration = verifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}

	private String[] getClaimsFromToken(String token) {
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getClaim(SecurityConstant.AUTHORITIES).asArray(String.class);
	}

	private JWTVerifier getJWTVerifier() {
		JWTVerifier verifier;
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);
			verifier = JWT.require(algorithm).withIssuer(SecurityConstant.GET_ARRAYS_LLC).build();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException(SecurityConstant.TOKEN_CANNOT_BE_VERIFIED);
		}
		return verifier;
	}

	private String[] getClaimsFromUser(UserPrincipal user) { // Claims or permisions
		List<String> authorities = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities.toArray(new String[0]);
		// List<String> authorities = user.getAuthorities()
		// .stream().map((authority) -> authority.getAuthority())
		// .collect(Collectors.toList());
		// return authorities.toArray(new String[0]);

		// List<String> authorities = user.getAuthorities()
		// .stream().map(GrantedAuthority::getAuthority)
		// .collect(Collectors.toList());
		// return authorities.toArray(new String[0]);
	};
}
