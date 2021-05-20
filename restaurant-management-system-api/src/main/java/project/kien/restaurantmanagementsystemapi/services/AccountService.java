package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.entities.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
}
