package project.kien.restaurantmanagementsystemapi.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.kien.restaurantmanagementsystemapi.entities.Account;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OpenSessionResponseDto {
    int sessionId;
    String sessionNumber;
    String position;
    SessionEnum status;
    Account creator;
    Account updater;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
