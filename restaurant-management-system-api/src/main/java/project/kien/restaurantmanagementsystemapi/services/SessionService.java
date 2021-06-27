package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.BillDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.SessionResDto;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;

public interface SessionService {
    OpenSessionResponseDto openSession(OpenSessionRequestDto request);

//    String closeSession(int sessionId, int updater);

    BillDto getBill(int sessionId);

//    boolean completeSession(int sessionId);

    boolean changeStatus(int sessionId, SessionEnum sessionEnum);

    SessionResDto getSessionOrders(int sessionId);

}
