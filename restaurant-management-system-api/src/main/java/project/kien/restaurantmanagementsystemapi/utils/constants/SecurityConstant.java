package project.kien.restaurantmanagementsystemapi.utils.constants;

public class SecurityConstant {

    public static final String LOGIN_URL = "/auth/login";
    public static final String SECRET = "JWTSuperSecretKey";
    public static final int EXPIRATION_TIME = 1 * 24 * 60 * 60 * 1000;
    public static final String TOKEN_TYPE = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String LIBRARIAN = "ROLE_LIBRARIAN";
    public static final String PATRON = "ROLE_PATRON";

}
