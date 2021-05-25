package project.kien.restaurantmanagementsystemapi.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OpenSessionResponseDto {
    String sessionNumber;
    String position;
    SessionEnum status;
    int createdBy;
    int updatedBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
