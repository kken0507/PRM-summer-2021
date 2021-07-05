package project.kien.restaurantmanagementsystemapi.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class OpenSessionRequestDto {
    @NotNull(message = "{openSession.position.notNull}")
    @Length(max = 100, message = "{openSession.position.length}")
    String position;
//    int creator;
}
