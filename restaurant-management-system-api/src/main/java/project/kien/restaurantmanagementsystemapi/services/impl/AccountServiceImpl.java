package project.kien.restaurantmanagementsystemapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.AccountReqDto;
import project.kien.restaurantmanagementsystemapi.entities.Account;
import project.kien.restaurantmanagementsystemapi.enums.RoleEnum;
import project.kien.restaurantmanagementsystemapi.exceptions.InvalidRequestException;
import project.kien.restaurantmanagementsystemapi.exceptions.ResourceNotFoundException;
import project.kien.restaurantmanagementsystemapi.mapper.AccountMapper;
import project.kien.restaurantmanagementsystemapi.repositories.AccountRepository;
import project.kien.restaurantmanagementsystemapi.services.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll().stream().map(acc -> accountMapper.toDtoWithoutPassword(acc)).collect(Collectors.toList());
    }

    @Override
    public Account findAccountByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "cannot find account with email: " + email));
    }

    @Override
    public AccountDto findAccountById(Integer id) {
        return accountMapper.toDtoWithoutPassword(
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Account", "cannot find account with id: " + id))
        );
    }

    @Override
    public boolean create(AccountReqDto dto) {
        boolean isAdded = false;

        if (dto != null) {

            if (repository.findByEmail(dto.getEmail()).isPresent()) {
                throw new InvalidRequestException("Duplicated email");
            }

            Account newAcc = accountMapper.toEntity(dto);

            String encryptedPass = encoder.encode(dto.getPassword());

            newAcc.setPassword(encryptedPass);

            repository.save(newAcc);
            isAdded = true;
        }

        return isAdded;
    }

    @Override
    public boolean update(Integer accId, AccountReqDto dto) {
        boolean isUpdated = false;

        Account item = repository.findById(accId)
                .orElseThrow(() -> new ResourceNotFoundException("Account",
                        "Cannot find Account with id: " + accId));

        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            item.setEmail(dto.getEmail());
            isUpdated = true;
        }
        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            if (RoleEnum.valueOf(dto.getRole()) != null) {
                item.setRole(dto.getRole());
                isUpdated = true;
            } else {
                throw new InvalidRequestException("Invalid role");
            }
        }
        if (dto.getIsActive() != null) {
            item.setIsActive(dto.getIsActive());
            isUpdated = true;
        }
        if (dto.getAvatar() != null && !dto.getAvatar().isEmpty()) {
            item.setAvatar(dto.getAvatar());
            isUpdated = true;
        }
        if (dto.getFullname() != null && !dto.getFullname().isEmpty()) {
            item.setFullname(dto.getFullname());
            isUpdated = true;
        }
        if (dto.getPhone() != null && !dto.getPhone().isEmpty()) {
            item.setPhone(dto.getPhone());
            isUpdated = true;
        }
        if (dto.getGender() != null && !dto.getGender().isEmpty()) {
            item.setGender(dto.getGender());
            isUpdated = true;
        }
        if (dto.getDob() != null) {
            item.setDob(dto.getDob());
            isUpdated = true;
        }

        repository.save(item);
        return isUpdated;
    }
}
