package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();
}
