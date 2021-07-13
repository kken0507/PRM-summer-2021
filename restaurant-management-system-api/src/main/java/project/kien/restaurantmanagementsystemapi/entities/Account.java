package project.kien.restaurantmanagementsystemapi.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Account extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "role", length = 20, nullable = false)
    private String role;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @Column(name = "avatar", length = 500)
    private String avatar;

    @Column(name = "fullname", length = 50, nullable = false)
    private String fullname;

    @Column(name = "phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "gender", length = 1, nullable = false)
    private String gender;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

}
