package project.kien.restaurantmanagementsystemapi.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OpenSessionRequestDto {
    String position;
    int creator;
}
