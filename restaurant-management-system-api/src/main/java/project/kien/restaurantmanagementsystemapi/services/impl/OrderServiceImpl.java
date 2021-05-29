package project.kien.restaurantmanagementsystemapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.request.OrderDetailReqDto;
import project.kien.restaurantmanagementsystemapi.entities.*;
import project.kien.restaurantmanagementsystemapi.enums.OrderEnum;
import project.kien.restaurantmanagementsystemapi.enums.SessionEnum;
import project.kien.restaurantmanagementsystemapi.exceptions.InvalidRequestException;
import project.kien.restaurantmanagementsystemapi.exceptions.ResourceNotFoundException;
import project.kien.restaurantmanagementsystemapi.repositories.*;
import project.kien.restaurantmanagementsystemapi.services.OrderService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ItemRepository itemRepository;


    @Override
    @Transactional
    public boolean create(List<OrderDetailReqDto> listItem, int sessionId) {
        boolean isAdded = false;

        if (listItem != null && !listItem.isEmpty()) {
            Order newOrder = new Order();

            Session session = sessionRepository.findById(sessionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Session",
                            "Cannot find Session type with id: " + sessionId));

            if (session.getStatus().equals(SessionEnum.OPENING)) {
                newOrder.setSession(session);
                newOrder = orderRepository.save(newOrder);
                if (newOrder.getId() != null) {
                    Set<Integer> unique = new HashSet<>();
                    listItem = listItem.stream()
                            .filter(item -> unique.add(item.getItemId()))
                            .collect(Collectors.toList());

                    for (OrderDetailReqDto item : listItem) {
                        Optional<Item> itemOpt = itemRepository.findById(item.getItemId());
                        if (itemOpt.isPresent()) {
                            OrderDetail newDetail = new OrderDetail();
                            newDetail.setOrder(newOrder);
                            newDetail.setItem(itemOpt.get());
                            newDetail.setQuantity(item.getQuantity());
                            orderDetailRepository.save(newDetail);
                        }
                    }

                    OrderStatus newStatus = new OrderStatus();
                    newStatus.setStatus(OrderEnum.PENDING);
                    newStatus.setOrder(newOrder);
                    orderStatusRepository.save(newStatus);

                    isAdded = true;
                }

            } else {
                throw new InvalidRequestException("Can not create order because Session: " + session.getStatus().name());
            }

        }

        return isAdded;
    }
}
