package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.response.CategoryResDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResDto> findAll();
}
