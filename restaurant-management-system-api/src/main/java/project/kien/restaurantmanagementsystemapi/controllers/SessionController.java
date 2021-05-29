package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.BillDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;
import project.kien.restaurantmanagementsystemapi.services.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @PostMapping("/openSession")
    public OpenSessionResponseDto openSession(OpenSessionRequestDto request) {
        return sessionService.openSession(request);
    }

    @PostMapping("/closeSession/{sessionId}")
    public String closeSession(@PathVariable("sessionId") int sessionId, int updater) {
        return sessionService.closeSession(sessionId, updater);
    }

    @GetMapping("/getBill/{sessionId}")
    public BillDto getBill(@PathVariable("sessionId") int sessionId) {
        return sessionService.getBill(sessionId);
    }
}
