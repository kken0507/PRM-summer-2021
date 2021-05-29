package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.kien.restaurantmanagementsystemapi.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Page<Item> findByNameLike(String name, Pageable pageable);

    Page<Item> findByNameLikeAndAndAvailable(String name, boolean isItemsAvailable, Pageable pageable);
    
    Page<Item> findAllByAvailable(boolean isItemsAvailable, Pageable pageable);
}
