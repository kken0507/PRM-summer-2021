package project.kien.restaurantmanagementsystemapi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;
import project.kien.restaurantmanagementsystemapi.entities.Session;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;
import project.kien.restaurantmanagementsystemapi.repositories.SessionRepository;
import project.kien.restaurantmanagementsystemapi.services.SessionService;
import project.kien.restaurantmanagementsystemapi.utils.tools.SessionNumberGenerator;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    @Transactional
    public OpenSessionResponseDto openSession(OpenSessionRequestDto request) {
        /*Open a new session so that the customer can start ordering.
         * */
        var newSession = objectMapper.convertValue(request, Session.class);
        newSession.setSessionNumber(new SessionNumberGenerator().generate(request.getPosition()));
        newSession.setStatus(SessionEnum.OPENING.name());
        newSession.setCreatedAt(LocalDateTime.now());
        /*=======================*/

        return objectMapper.convertValue(sessionRepository.save(newSession), OpenSessionResponseDto.class);
    }
}
