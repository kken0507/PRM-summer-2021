package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.request.OrderDetailReqDto;
import project.kien.restaurantmanagementsystemapi.enums.OrderEnum;

import java.util.List;

public interface OrderService {
    boolean create(List<OrderDetailReqDto> listItem, int sessionId);

    boolean changeStatus(int orderId, OrderEnum orderEnum, String reason);

    boolean declineOrder(int orderId, String reason);

    boolean dropOrder(int orderId, String reason);

    boolean confirmOrder(int orderId);
}
