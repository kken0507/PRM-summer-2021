package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.request.OrderDetailReqDto;

import java.util.List;

public interface OrderService {
    boolean create(List<OrderDetailReqDto> listItem, int sessionId);
}
