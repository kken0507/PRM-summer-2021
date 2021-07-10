package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.kien.restaurantmanagementsystemapi.entities.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Page<Item> findByNameLike(String name, Pageable pageable);

    List<Item> findByNameLike(String name);

    Page<Item> findByNameLikeAndIsAvailable(String name, boolean isItemsAvailable, Pageable pageable);

    List<Item> findByNameLikeAndIsAvailable(String name, boolean isItemsAvailable);

    Page<Item> findAllByIsAvailable(boolean isItemsAvailable, Pageable pageable);

    List<Item> findAllByIsAvailable(boolean isItemsAvailable);

}
