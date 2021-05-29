package project.kien.restaurantmanagementsystemapi.services;

import java.util.List;

public interface OrderService {
    boolean create(List<Integer> listItemId);
}
