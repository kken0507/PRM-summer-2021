package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kien.restaurantmanagementsystemapi.entities.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
}
