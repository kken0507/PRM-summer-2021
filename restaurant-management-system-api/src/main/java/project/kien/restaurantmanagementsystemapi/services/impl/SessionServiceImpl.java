package project.kien.restaurantmanagementsystemapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.repositories.SessionRepository;
import project.kien.restaurantmanagementsystemapi.services.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository sessionRepository;
}
