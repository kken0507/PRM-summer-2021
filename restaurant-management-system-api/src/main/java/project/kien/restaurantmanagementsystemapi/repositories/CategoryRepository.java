package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.kien.restaurantmanagementsystemapi.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Page<Category> findByNameLike(String name, Pageable pageable);

    @Query(
            "select c from Category c join Item i on c.id = i.category.id where c.name like ?1 and i.isAvailable = ?2"
    )
    Page<Category> findByNameLikeAndByItemsIsAvailable(String name, boolean isItemsAvailable, Pageable pageable);

    @Query(
            "select c from Category c join Item i on c.id = i.category.id where i.isAvailable = ?1"
    )
    Page<Category> findAllAndByItemsIsAvailable(boolean isItemsAvailable, Pageable pageable);

}