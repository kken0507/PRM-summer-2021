package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.kien.restaurantmanagementsystemapi.entities.Account;
import project.kien.restaurantmanagementsystemapi.security.JwtTokenProvider;
import project.kien.restaurantmanagementsystemapi.security.payload.LoginRequest;
import project.kien.restaurantmanagementsystemapi.security.payload.LoginResponse;
import project.kien.restaurantmanagementsystemapi.services.AccountService;
import project.kien.restaurantmanagementsystemapi.utils.constants.SecurityConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountService accountService;

    @PostMapping(SecurityConstant.LOGIN_URL)
    public LoginResponse checkLogin(@RequestBody @Valid LoginRequest loginRequest,
                                    HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = accountService.findAccountByEmail(loginRequest.getEmail());
        String jwt = tokenProvider.generateToken(authentication);
        String role = account.getRole();
        String avatar = account.getAvatar();
        LoginResponse payload = new LoginResponse(
                jwt,
                tokenProvider.getUserIdFromJwt(jwt),
                tokenProvider.getExpiryDateFromJwt(jwt),
                role,
                loginRequest.getEmail(),
                avatar
        );
        //set cookies
        Cookie cookie = new Cookie(SecurityConstant.HEADER_STRING, jwt);
        cookie.setPath("/");
        cookie.setMaxAge(SecurityConstant.EXPIRATION_TIME / 1000);
        response.addCookie(cookie);

        return payload;
    }

    @GetMapping("/auth/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(SecurityConstant.HEADER_STRING, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "Logout Success";
    }

}
