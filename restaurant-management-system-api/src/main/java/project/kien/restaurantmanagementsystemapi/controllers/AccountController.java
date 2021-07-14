package project.kien.restaurantmanagementsystemapi.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;
import project.kien.restaurantmanagementsystemapi.dtos.common.ErrorDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.AccountReqDto;
import project.kien.restaurantmanagementsystemapi.services.AccountService;
import project.kien.restaurantmanagementsystemapi.utils.constants.ConstantUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @ApiOperation(value = "This API create new account")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody(required = true) AccountReqDto newAcc) {

        boolean bool = service.create(newAcc);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to create new account");

        return new ResponseEntity(bool ? ConstantUtil.CREATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value = "This API update a account by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing input", response = ErrorDto.class)})
    @PostMapping("/update/{accId}")
    public ResponseEntity<?> update(@NotNull @PathVariable("accId") int id, @RequestBody(required = true) AccountReqDto acc) {

        boolean bool = service.update(id, acc);

        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                "Failed to update the account");

        return new ResponseEntity(bool ? ConstantUtil.UPDATE_SUCCESS : error,
                bool ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}