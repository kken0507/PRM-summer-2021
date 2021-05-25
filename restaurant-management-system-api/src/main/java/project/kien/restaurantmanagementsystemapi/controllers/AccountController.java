package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;
import project.kien.restaurantmanagementsystemapi.services.AccountService;

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
}