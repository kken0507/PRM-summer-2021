package project.kien.restaurantmanagementsystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kien.restaurantmanagementsystemapi.entities.Account;
import project.kien.restaurantmanagementsystemapi.repositories.AccountRepository;
import project.kien.restaurantmanagementsystemapi.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    AccountService service;

    @GetMapping("/all")
    public List<Account> findAllBooks() {
        return service.findAll();
    }
}