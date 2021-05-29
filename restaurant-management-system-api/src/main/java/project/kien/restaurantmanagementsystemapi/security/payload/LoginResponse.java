package project.kien.restaurantmanagementsystemapi.security.payload;


import lombok.Getter;
import lombok.Setter;
import project.kien.restaurantmanagementsystemapi.utils.constants.SecurityConstant;

import java.util.Date;

@Getter
@Setter

public class LoginResponse {

    private String accessToken;

    private final String tokenType = SecurityConstant.TOKEN_TYPE;

    private Integer userId;

    private Date expiryDate;

    private String role;

    private String email;

    private String avatar;

    public LoginResponse(String accessToken, Integer userId, Date expiryDate, String role, String email, String avatar) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.expiryDate = expiryDate;
        this.role = role;
        this.email = email;
        this.avatar = avatar;
    }
}
