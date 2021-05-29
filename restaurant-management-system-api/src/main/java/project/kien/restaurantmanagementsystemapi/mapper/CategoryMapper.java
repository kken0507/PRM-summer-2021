package project.kien.restaurantmanagementsystemapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import project.kien.restaurantmanagementsystemapi.dtos.common.CategoryDto;
import project.kien.restaurantmanagementsystemapi.dtos.request.CategoryReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.CategoryResDto;
import project.kien.restaurantmanagementsystemapi.entities.Category;

@Mapper(uses = {ItemMapper.class}, componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "items", qualifiedByName = "noCategory")
    CategoryDto toDto(Category entity);

    CategoryResDto toResDto(Category entity);

    Category toEntity(CategoryReqDto entity);

    @Named("noItems")
    @Mapping(target = "items", ignore = true)
    CategoryDto toDtoWithoutItem(Category entity);

}
