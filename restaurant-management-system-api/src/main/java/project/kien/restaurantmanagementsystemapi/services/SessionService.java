package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.BillDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;

public interface SessionService {
    OpenSessionResponseDto openSession(OpenSessionRequestDto request);

    String closeSession(int sessionId, int updater);

    BillDto getBill(int sessionId);

}
