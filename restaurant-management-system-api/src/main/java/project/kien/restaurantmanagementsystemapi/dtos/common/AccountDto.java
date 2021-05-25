package project.kien.restaurantmanagementsystemapi.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Integer id;

    @NotNull(message = "{account.email.notNull}")
    @Length(max = 100, message = "{account.email.length}")
    private String email;

    @NotNull(message = "{account.password.notNull}")
    @Length(max = 100, message = "{account.password.length}")
    private String password;

    @NotNull(message = "{account.role.notNull}")
    @Length(max = 20, message = "{account.role.length}")
    private String role;

    @NotNull(message = "{account.isActive.notNull}")
    private boolean isActive;

    @Length(max = 500, message = "{account.avatar.length}")
    private String avatar;

    @NotNull(message = "{account.fullname.notNull}")
    @Length(max = 50, message = "{account.fullname.length}")
    private String fullname;

    @NotNull(message = "{account.phone.notNull}")
    @Length(max = 10, message = "{account.phone.length}")
    private String phone;

    @NotNull(message = "{account.gender.notNull}")
    @Length(max = 1, message = "{account.gender.length}")
    private String gender;

    @NotNull(message = "{account.dob.notNull}")
    private LocalDate dob;
}