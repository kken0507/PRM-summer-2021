package project.kien.restaurantmanagementsystemapi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.OpenSessionResponseDto;
import project.kien.restaurantmanagementsystemapi.entities.Session;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;
import project.kien.restaurantmanagementsystemapi.exceptions.ResourceNotFoundException;
import project.kien.restaurantmanagementsystemapi.repositories.AccountRepository;
import project.kien.restaurantmanagementsystemapi.repositories.SessionRepository;
import project.kien.restaurantmanagementsystemapi.services.SessionService;
import project.kien.restaurantmanagementsystemapi.utils.tools.SessionNumberGenerator;

import javax.transaction.Transactional;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ObjectMapper objectMapper;

    static private final String SESSION = "SESSION";
    static private final String ACCOUNT = "ACCOUNT";
    static private final String SESSION_NOT_FOUND = "Session is not found";
    static private final String CREATOR_NOT_FOUND = "Creator is not found";
    static private final String UPDATER_NOT_FOUND = "Updater is not found";


    @Override
    @Transactional
    public OpenSessionResponseDto openSession(OpenSessionRequestDto request) {
        /*Open a new session so that the customer can start ordering.
         * */
        var newSession = objectMapper.convertValue(request, Session.class);
        newSession.setSessionNumber(new SessionNumberGenerator().generate(request.getPosition()));
        newSession.setStatus(SessionEnum.OPENING);
//        newSession.setCreatedAt(LocalDateTime.now());
//        newSession.setCreatedBy(accountRepository.findById(request.getCreator()).orElseThrow(() ->
//                new ResourceNotFoundException(ACCOUNT, CREATOR_NOT_FOUND)).getId());
        /*=======================*/

        return objectMapper.convertValue(sessionRepository.save(newSession), OpenSessionResponseDto.class);
    }

    @Override
    @Transactional
    public String closeSession(int sessionId, int updater) {
        var session = sessionRepository.findById(sessionId).
                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND));
        session.setStatus(SessionEnum.CLOSED);
//        session.setUpdatedAt(LocalDateTime.now());
//        session.setUpdatedBy(accountRepository.findById(updater).orElseThrow(() ->
//                new ResourceNotFoundException(ACCOUNT, UPDATER_NOT_FOUND)).getId());
        sessionRepository.save(session);
        return "success";
    }
}
