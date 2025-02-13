package com.SpringSecurityRoles.constants;

public class SecurityConstant {
	
	public static final long EXPIRATION_TIME = 432_000_000; // 5 DÍAS EXPRESADO EN MILESIGUNDOS
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token no se puede verificar";
	public static final String GET_ARRAYS_LLC = "Get array, LLC";
	public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
	public static final String AUTHORITIES = "Athorities";
	public static final String FORBIDDEN_MESSAGE = "Usted no tiene permisos para acceder a esta pagina";
	public static final String ACCES_DENIED_MESSAGE = "Usted no tine permisos para accder a esta pagina";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String[] PUBLIC_URLS = { "/user/login", "/user/register", "/user/resetpassword/**", "/user/image/**" };
	// public static final String[] PUBLIC_URLS = { "/**" };

}
