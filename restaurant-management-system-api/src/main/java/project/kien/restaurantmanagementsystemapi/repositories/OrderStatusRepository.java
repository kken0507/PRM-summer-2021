package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kien.restaurantmanagementsystemapi.entities.OrderStatus;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

    Optional<OrderStatus> getDistinctTopByCustomerOrder_IdOrderByCreatedAtDesc(Integer orderId);

}
