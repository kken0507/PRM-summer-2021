package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;
import project.kien.restaurantmanagementsystemapi.services.AccountService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService service;

    @GetMapping("/all")
    public List<AccountDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{accountId}")
    public AccountDto getAccount(@NotNull @PathVariable("accountId") int id) {
        return service.findAccountById(id);
    }

}