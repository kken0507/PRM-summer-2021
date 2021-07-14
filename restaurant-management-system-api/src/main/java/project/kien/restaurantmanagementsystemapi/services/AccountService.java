package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.AccountReqDto;
import project.kien.restaurantmanagementsystemapi.entities.Account;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();

    Account findAccountByEmail(String email);

    AccountDto findAccountById(Integer id);

    boolean create(AccountReqDto dto);

    boolean update(Integer accId, AccountReqDto dto);
}
