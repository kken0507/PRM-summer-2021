package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kien.restaurantmanagementsystemapi.entities.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

}
