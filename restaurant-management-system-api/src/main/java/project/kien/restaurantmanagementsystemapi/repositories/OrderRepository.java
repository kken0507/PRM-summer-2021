package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kien.restaurantmanagementsystemapi.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
