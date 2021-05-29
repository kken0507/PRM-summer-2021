package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.kien.restaurantmanagementsystemapi.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
