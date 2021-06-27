package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kien.restaurantmanagementsystemapi.dtos.common.ErrorDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.BillDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.SessionResDto;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;
import project.kien.restaurantmanagementsystemapi.services.SessionService;
import project.kien.restaurantmanagementsystemapi.utils.constants.ConstantUtil;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @PostMapping("/openSession")
    public OpenSessionResponseDto openSession(OpenSessionRequestDto request) {
        return sessionService.openSession(request);
    }

//    @PostMapping("/closeSession/{sessionId}")
//    public String closeSession(@PathVariable("sessionId") int sessionId, int updater) {
//        return sessionService.closeSession(sessionId, updater);
//    }

    @GetMapping("/getBill/{sessionId}")
    public BillDto getBill(@PathVariable("sessionId") int sessionId) {
        return sessionService.getBill(sessionId);
    }

    @PostMapping("/closeSession/{sessionId}")
    public ResponseEntity<?> closeSession(@PathVariable("sessionId") int sessionId) {
        boolean bool = sessionService.changeStatus(sessionId, SessionEnum.CLOSED);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to close the session");

        return new ResponseEntity(bool ? ConstantUtil.UPDATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/completeSession/{sessionId}")
    public ResponseEntity<?> completeSession(@PathVariable("sessionId") int sessionId) {
        boolean bool = sessionService.changeStatus(sessionId, SessionEnum.COMPLETED);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to complete the session");

        return new ResponseEntity(bool ? ConstantUtil.UPDATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getSessionOrders/{sessionId}")
    public SessionResDto getSessionOrders(@PathVariable("sessionId") int sessionId) {
        return sessionService.getSessionOrders(sessionId);
    }
}
