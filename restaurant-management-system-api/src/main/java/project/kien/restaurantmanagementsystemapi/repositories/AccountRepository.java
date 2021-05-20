package project.kien.restaurantmanagementsystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.kien.restaurantmanagementsystemapi.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
