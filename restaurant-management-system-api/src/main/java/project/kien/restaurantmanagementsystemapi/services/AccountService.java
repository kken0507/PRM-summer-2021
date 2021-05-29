package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;
import project.kien.restaurantmanagementsystemapi.entities.Account;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();

    Account findAccountByEmail(String email);

}
