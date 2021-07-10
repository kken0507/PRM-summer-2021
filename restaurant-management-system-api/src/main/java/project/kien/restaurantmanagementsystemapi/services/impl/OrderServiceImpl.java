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
                            newDetail.setCustomerOrder(newOrder);
                            newDetail.setItem(itemOpt.get());
                            newDetail.setQuantity(item.getQuantity());
                            orderDetailRepository.save(newDetail);
                        }
                    }

                    OrderStatus newStatus = new OrderStatus();
                    newStatus.setStatus(OrderEnum.PENDING);
                    newStatus.setCustomerOrder(newOrder);
                    orderStatusRepository.save(newStatus);

                    isAdded = true;
                }

            } else {
                throw new InvalidRequestException("Can not create order because Session: " + session.getStatus().name());
            }

        }

        return isAdded;
    }

    @Override
    public boolean changeStatus(int orderId, OrderEnum orderEnum, String reason) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException("Order", "Order is not found"));

        OrderStatus newStatus = new OrderStatus();
        newStatus.setStatus(orderEnum);
        newStatus.setCustomerOrder(order);
        if (reason != null && !reason.isEmpty()) {
            newStatus.setContent(reason);
        }
        orderStatusRepository.save(newStatus);

        return true;
    }

    @Override
    public boolean declineOrder(int orderId, String reason) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException("Order", "Order is not found"));

        OrderStatus curOrderStatus = orderStatusRepository.getDistinctTopByCustomerOrder_IdOrderByCreatedAtDesc(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatus", "Current order status is not found"));

        if (curOrderStatus.getStatus().equals(OrderEnum.PENDING)) {

            changeStatus(orderId, OrderEnum.DECLINED, reason);

            return true;

        } else if (curOrderStatus.getStatus() != null && !curOrderStatus.getStatus().toString().isEmpty()) {
            throw new InvalidRequestException("Can not decline order because status is " + curOrderStatus.getStatus());
        }

        return false;
    }

    @Override
    public boolean dropOrder(int orderId, String reason) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException("Order", "Order is not found"));

        OrderStatus curOrderStatus = orderStatusRepository.getDistinctTopByCustomerOrder_IdOrderByCreatedAtDesc(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatus", "Current order status is not found"));

        if (curOrderStatus.getStatus().equals(OrderEnum.CONFIRMED)) {

            changeStatus(orderId, OrderEnum.DROPPED, reason);

            return true;

        } else if (curOrderStatus.getStatus() != null && !curOrderStatus.getStatus().toString().isEmpty()) {
            throw new InvalidRequestException("Can not drop order because status is " + curOrderStatus.getStatus());
        }

        return false;
    }

    @Override
    public boolean confirmOrder(int orderId) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException("Order", "Order is not found"));

        OrderStatus curOrderStatus = orderStatusRepository.getDistinctTopByCustomerOrder_IdOrderByCreatedAtDesc(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatus", "Current order status is not found"));

        if (curOrderStatus.getStatus().equals(OrderEnum.PENDING)) {

            changeStatus(orderId, OrderEnum.CONFIRMED, "");

            return true;

        } else if (curOrderStatus.getStatus() != null && !curOrderStatus.getStatus().toString().isEmpty()) {
            throw new InvalidRequestException("Can not confirmed order because status is " + curOrderStatus.getStatus());
        }

        return false;
    }

    public boolean serveOrder(int orderId) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundException("Order", "Order is not found"));

        OrderStatus curOrderStatus = orderStatusRepository.getDistinctTopByCustomerOrder_IdOrderByCreatedAtDesc(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderStatus", "Current order status is not found"));

        if (curOrderStatus.getStatus().equals(OrderEnum.CONFIRMED)) {

            changeStatus(orderId, OrderEnum.SERVED, "");

            return true;

        } else if (curOrderStatus.getStatus() != null && !curOrderStatus.getStatus().toString().isEmpty()) {
            throw new InvalidRequestException("Can not confirmed order because status is " + curOrderStatus.getStatus());
        }

        return false;
    }
}
