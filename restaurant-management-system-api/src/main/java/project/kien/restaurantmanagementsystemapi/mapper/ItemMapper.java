package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.Mapper;
import project.kien.restaurantmanagementsystemapi.dtos.common.ItemDto;
import project.kien.restaurantmanagementsystemapi.entities.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    //    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    ItemDto toDto(Item entity);

}
