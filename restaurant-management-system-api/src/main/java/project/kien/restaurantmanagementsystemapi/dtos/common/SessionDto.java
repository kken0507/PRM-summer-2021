package project.kien.restaurantmanagementsystemapi.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    @NotNull(message = "{session.id.notNull}")
    private Integer id;

    @NotNull(message = "{session.sessionNumber.notNull}")
    @Length(max = 100, message = "{session.sessionNumber.length}")
    private String sessionNumber;

    @NotNull(message = "{session.position.notNull}")
    @Length(max = 100, message = "{session.position.length}")
    private String position;

    @NotNull(message = "{session.status.notNull}")
    @Length(max = 50, message = "{session.status.length}")
    private SessionEnum status;

    private Set<OrderDto> orders;
}
