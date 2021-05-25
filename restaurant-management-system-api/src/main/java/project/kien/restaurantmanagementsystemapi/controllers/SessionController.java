package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;
import project.kien.restaurantmanagementsystemapi.services.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @PostMapping("/openSession")
    public OpenSessionResponseDto openSession(OpenSessionRequestDto request){
        return sessionService.openSession(request);
    }

    @PostMapping("/closeSession/{sessionId}")
    public String closeSession(@PathVariable("sessionId") int sessionId, int updater){
        return sessionService.closeSession(sessionId, updater);
    }
}
