package project.kien.restaurantmanagementsystemapi.services;

import project.kien.restaurantmanagementsystemapi.dtos.res.CategoryResDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResDto> findAll();
}
