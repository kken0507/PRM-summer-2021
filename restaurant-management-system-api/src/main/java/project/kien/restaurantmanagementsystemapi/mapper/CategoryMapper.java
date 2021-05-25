package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.Mapper;
import project.kien.restaurantmanagementsystemapi.dtos.common.CategoryDto;
import project.kien.restaurantmanagementsystemapi.dtos.res.CategoryResDto;
import project.kien.restaurantmanagementsystemapi.entities.Category;

@Mapper(uses = {ItemMapper.class}, componentModel = "spring")
public interface CategoryMapper {

    //    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    CategoryDto toDto(Category entity);

    CategoryResDto toResDto(Category entity);

}
