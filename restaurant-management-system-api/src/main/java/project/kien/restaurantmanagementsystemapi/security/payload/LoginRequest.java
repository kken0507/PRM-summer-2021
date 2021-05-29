package project.kien.restaurantmanagementsystemapi.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
@NotNull
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
