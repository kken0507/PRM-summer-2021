package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import project.kien.restaurantmanagementsystemapi.dtos.common.ItemDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.ItemReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.ItemResDto;
import project.kien.restaurantmanagementsystemapi.entities.Item;

import java.util.Set;

@Mapper(uses = {CategoryMapper.class}, componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "category", qualifiedByName = "noItems")
    ItemDto toDto(Item entity);

    ItemResDto toResDto(Item entity);

    Item toEntity(ItemReqDto dto);

    @Named("noCategory")
    @Mapping(target = "category", ignore = true)
    ItemDto toDtoWithoutCategory(Item entity);

    @Named("noCategory")
    @IterableMapping(qualifiedByName = "noCategory")
    Set<ItemDto> tolistDtoWithoutCategory(Set<Item> entity);

}
