package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.kien.restaurantmanagementsystemapi.dtos.common.AccountDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.AccountReqDto;
import project.kien.restaurantmanagementsystemapi.entities.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

//    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account entity);

    @Mapping(target = "password", ignore = true)
    AccountDto toDtoWithoutPassword(Account entity);

    Account toEntity(AccountReqDto dto);

}
