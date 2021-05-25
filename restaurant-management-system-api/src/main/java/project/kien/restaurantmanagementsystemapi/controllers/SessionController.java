package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kien.restaurantmanagementsystemapi.services.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

//    @PostMapping("/openSession")
//    public String openSession(){
//        return sessionService.openSession();
//    }
}
