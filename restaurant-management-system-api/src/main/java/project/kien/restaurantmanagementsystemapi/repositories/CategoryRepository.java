package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kien.restaurantmanagementsystemapi.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}