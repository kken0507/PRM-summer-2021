package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.BillDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.SessionResDto;
import project.kien.restaurantmanagementsystemapi.enums.OrderEnum;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;

import java.util.List;

public interface SessionService {
    OpenSessionResponseDto openSession(OpenSessionRequestDto request);

//    String closeSession(int sessionId, int updater);

    BillDto getBill(int sessionId);

    BillDto getBillBySessionNum(String sessionNum);

//    boolean completeSession(int sessionId);

    boolean changeStatus(int sessionId, SessionEnum sessionEnum);

    SessionResDto getSession(int sessionId);

    List<SessionResDto> getOpeningSessionsByOrderStatus(OrderEnum status);

    SessionResDto getOpeningSessionOrdersByOrderStatusAndSessionId(int sessionId, OrderEnum status);

    boolean closeSession(int sessionId);

    boolean completeSession(int sessionId);

    BillDto getBillBySessionNumForClosedSession(String sessionNum);

}
