package project.kien.restaurantmanagementsystemapi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.common.ItemDto;
import project.kien.restaurantmanagementsystemapi.dtos.common.OrderDto;
import project.kien.restaurantmanagementsystemapi.dtos.common.OrderStatusDto;
import project.kien.restaurantmanagementsystemapi.dtos.common.SessionDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.OpenSessionRequestDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.*;
import project.kien.restaurantmanagementsystemapi.entities.Session;
import project.kien.restaurantmanagementsystemapi.enums.OrderEnum;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;
import project.kien.restaurantmanagementsystemapi.exceptions.ResourceNotFoundException;
import project.kien.restaurantmanagementsystemapi.mapper.SessionMapper;
import project.kien.restaurantmanagementsystemapi.repositories.AccountRepository;
import project.kien.restaurantmanagementsystemapi.repositories.SessionRepository;
import project.kien.restaurantmanagementsystemapi.services.SessionService;
import project.kien.restaurantmanagementsystemapi.utils.tools.SessionNumberGenerator;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    SessionMapper sessionMapper;

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

        return sessionMapper.toOpenSessionResponseDto(sessionRepository.save(newSession));
    }

//    @Override
//    @Transactional
//    public String closeSession(int sessionId, int updater) {
//        var session = sessionRepository.findById(sessionId).
//                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND));
//        session.setStatus(SessionEnum.CLOSED);
////        session.setUpdatedAt(LocalDateTime.now());
////        session.setUpdatedBy(accountRepository.findById(updater).orElseThrow(() ->
////                new ResourceNotFoundException(ACCOUNT, UPDATER_NOT_FOUND)).getId());
//        sessionRepository.save(session);
//        return "success";
//    }

//    @Override
//    public boolean completeSession(int sessionId) {
//        var session = sessionRepository.findById(sessionId).
//                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND));
//        session.setStatus(SessionEnum.COMPLETED);
//        sessionRepository.save(session);
//        return true;
//    }

    @Override
    public BillDto getBill(int sessionId) {
        BillDto billDto = new BillDto();
        SessionDto sessionDto = sessionMapper.toDto(sessionRepository.findById(sessionId).
                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND)));
        Set<OrderDto> tmpOrders = getServedOrders(sessionDto);
        sessionDto.setOrders(tmpOrders);
        billDto.setTotalPrice(calculateTotalPrice(sessionDto.getOrders()));
        billDto.setTotalItemQuantity(calculateTotalItemQuantity(sessionDto.getOrders()));
        billDto.setSession(sessionDto);
        billDto.setItems(getServedItems(tmpOrders));

        return billDto;
    }

    @Override
    public BillDto getBillBySessionNum(String sessionNum) {
        BillDto billDto = new BillDto();
        SessionDto sessionDto = sessionMapper.toDto(sessionRepository.findBySessionNumber(sessionNum).
                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND)));
        Set<OrderDto> tmpOrders = getServedOrders(sessionDto);
        sessionDto.setOrders(tmpOrders);
        billDto.setTotalPrice(calculateTotalPrice(sessionDto.getOrders()));
        billDto.setTotalItemQuantity(calculateTotalItemQuantity(sessionDto.getOrders()));
        billDto.setSession(sessionDto);
        billDto.setItems(getServedItems(tmpOrders));

        return billDto;
    }

    private Set<OrderDto> getServedOrders(SessionDto sessionDto) {
        return sessionDto.getOrders()
                .stream()
                .filter(orderDto -> orderDto.getOrderStatus()
                        .stream()
                        .min(Comparator.comparing(OrderStatusDto::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                        .get().getStatus().equals(OrderEnum.SERVED))
                .collect(Collectors.toSet());
    }

    private List<ItemBillDto> getServedItems(Set<OrderDto> servedOrders) {
        Map<Integer, Integer> quantities = new HashMap<>();
        Map<Integer, ItemDto> items = new HashMap<>();
        List<ItemBillDto> res = new ArrayList<>();
        servedOrders.stream().forEach(orderDto -> {
            orderDto.getOrderDetails().stream().forEach(orderDetailDto -> {
                if (quantities.containsKey(orderDetailDto.getItem().getId())) {
                    int oldValue = quantities.get(orderDetailDto.getItem().getId());
                    quantities.replace(orderDetailDto.getItem().getId(), (oldValue + orderDetailDto.getQuantity()));
                } else {
                    quantities.put(orderDetailDto.getItem().getId(), orderDetailDto.getQuantity());
                    items.put(orderDetailDto.getItem().getId(), orderDetailDto.getItem());
                }
            });
        });

        quantities.forEach((id, quantity) -> res.add(new ItemBillDto(quantity, items.get(id))));

        return res;
    }

    @Override
    public boolean changeStatus(int sessionId, SessionEnum sessionEnum) {
        var session = sessionRepository.findById(sessionId).
                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND));
        session.setStatus(sessionEnum);
        sessionRepository.save(session);
        return true;
    }


    private Double calculateTotalPrice(Set<OrderDto> orderDtos) {
        double totalPrice = 0;
        for (OrderDto dto :
                orderDtos) {
            totalPrice += dto.getOrderDetails()
                    .stream()
                    .mapToDouble(orderDetailDto -> orderDetailDto.getQuantity() * orderDetailDto.getItem().getPrice()).sum();
        }

        return totalPrice;
    }

    private Integer calculateTotalItemQuantity(Set<OrderDto> orderDtos) {
        int totalquantity = 0;
        for (OrderDto dto :
                orderDtos) {
            totalquantity += dto.getOrderDetails()
                    .stream()
                    .mapToDouble(orderDetailDto -> orderDetailDto.getQuantity()).sum();
        }

        return totalquantity;
    }

    @Override
    public SessionResDto getSession(int sessionId) {
        SessionResDto sessionDto = sessionMapper.toResDto(sessionRepository.findById(sessionId).
                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND)));
        List<OrderResDto> orders = sessionDto.getOrders().stream().collect(Collectors.toList());
        orders.stream().forEach(orderDto -> orderDto.setCurOrderStatus(orderDto.getOrderStatus().stream().min(Comparator.comparing(OrderStatusDto::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder()))).get()));
        return sessionDto;
    }

    @Override
    public List<SessionResDto> getOpeningSessionsByOrderStatus(OrderEnum status) {
        List<Session> sessionEntities = sessionRepository.findAllByStatus(SessionEnum.OPENING);
        List<SessionResDto> sessionDtos = sessionMapper.toResDtos(sessionEntities);
//        sessionDtos = sessionDtos.stream()
//                .filter(sessionResDto -> sessionResDto.getOrders().stream().anyMatch(orderResDto -> orderResDto.getOrderStatus().stream().min(Comparator.comparing(OrderStatusDto::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder()))).get().getStatus().equals(status)))
//                .collect(Collectors.toList());
        sessionDtos.stream()
                .forEach(sessionResDto -> sessionResDto.getOrders().stream().collect(Collectors.toList()).stream().forEach(orderDto -> orderDto.setCurOrderStatus(orderDto.getOrderStatus().stream().min(Comparator.comparing(OrderStatusDto::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder()))).get())));
        sessionDtos.stream().forEach(sessionResDto -> sessionResDto.setOrders(sessionResDto.getOrders().stream().filter(orderResDto -> orderResDto.getCurOrderStatus().getStatus().equals(status)).collect(Collectors.toSet())));
        sessionDtos = sessionDtos.stream().filter(sessionResDto -> sessionResDto.getOrders().stream().anyMatch(orderResDto -> orderResDto.getCurOrderStatus().getStatus().equals(status))).collect(Collectors.toList());
        return sessionDtos;
    }

    @Override
    public SessionResDto getOpeningSessionOrdersByOrderStatusAndSessionId(int sessionId, OrderEnum status) {
        SessionResDto sessionDto = sessionMapper.toResDto(sessionRepository.findSessionByIdAndAndStatus(sessionId, SessionEnum.OPENING).
                orElseThrow(() -> new ResourceNotFoundException(SESSION, SESSION_NOT_FOUND)));
        List<OrderResDto> orders = sessionDto.getOrders().stream().collect(Collectors.toList());
        orders.stream().forEach(orderDto -> orderDto.setCurOrderStatus(orderDto.getOrderStatus().stream().min(Comparator.comparing(OrderStatusDto::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder()))).get()));
        sessionDto.setOrders(sessionDto.getOrders().stream().filter(orderResDto -> orderResDto.getCurOrderStatus().getStatus().equals(status)).collect(Collectors.toSet()));
        return sessionDto;
    }
}
