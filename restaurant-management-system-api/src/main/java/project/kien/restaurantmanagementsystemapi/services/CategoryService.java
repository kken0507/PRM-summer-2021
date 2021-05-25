package project.kien.restaurantmanagementsystemapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.kien.restaurantmanagementsystemapi.dtos.request.CategoryReqDto;
import project.kien.restaurantmanagementsystemapi.dtos.response.CategoryResDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResDto> findAll();

    boolean create(CategoryReqDto dto);

    boolean update(Integer categoryId, CategoryReqDto dto);

    boolean delete(Integer id);

    Page<CategoryResDto> search(String name, Boolean isItemAvailable, Pageable pageable);
}
